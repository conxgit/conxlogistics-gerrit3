package com.conx.logistics.app.whse.rcv.rcv.dao.services.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conx.logistics.app.whse.domain.warehouse.Warehouse;
import com.conx.logistics.app.whse.rcv.asn.domain.ASN;
import com.conx.logistics.app.whse.rcv.asn.domain.ASNDropOff;
import com.conx.logistics.app.whse.rcv.asn.domain.ASNLine;
import com.conx.logistics.app.whse.rcv.asn.domain.ASNPickup;
import com.conx.logistics.app.whse.rcv.rcv.dao.services.IReceiveDAOService;
import com.conx.logistics.app.whse.rcv.rcv.domain.DropOff;
import com.conx.logistics.app.whse.rcv.rcv.domain.Pickup;
import com.conx.logistics.app.whse.rcv.rcv.domain.Receive;
import com.conx.logistics.app.whse.rcv.rcv.domain.ReceiveLine;
import com.conx.logistics.app.whse.rcv.rcv.domain.types.RECEIVELINESTATUS;
import com.conx.logistics.kernel.documentlibrary.remote.services.IRemoteDocumentRepository;
import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;
import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.mdm.domain.documentlibrary.DocType;
import com.conx.logistics.mdm.domain.documentlibrary.FileEntry;
import com.conx.logistics.mdm.domain.documentlibrary.Folder;
import com.conx.logistics.mdm.domain.note.Note;
import com.conx.logistics.mdm.domain.note.NoteItem;
import com.conx.logistics.mdm.domain.note.NoteType;
import com.conx.logistics.mdm.domain.product.Product;


/**
 * Implementation of {@link Receive} that uses JPA for persistence.<p />
 * <p/>
 * This class is marked as {@link Transactional}. The Spring configuration for this module, enables AspectJ weaving for
 * adding transaction demarcation to classes annotated with <code>@Transactional</code>.
 */
@Transactional
@Repository
public class ReceiveDAOImpl implements IReceiveDAOService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
    /**
     * Spring will inject a managed JPA {@link EntityManager} into this field.
     */
    @PersistenceContext
    private EntityManager em;	
    
    @Autowired
    private IRemoteDocumentRepository documentRepositoryService;
    
    @Autowired
    private IEntityTypeDAOService entityTypeDAOService;
    
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Receive get(long id) {
		return em.getReference(Receive.class, id);
	}    

	@Override
	public List<Receive> getAll() {
		return em.createQuery("select o from com.conx.logistics.app.whse.rcv.rcv.domain.Receive o",Receive.class).getResultList();
	}	

	@Override
	public Receive add(Receive record) {
		record = em.merge(record);
		return record;
	}

	@Override
	public void delete(Receive record) {
		em.remove(record);
	}

	@Override
	public Receive update(Receive record) {
		return em.merge(record);
	}

	public Receive addLines(Long rcvId, Set<ReceiveLine> lines) throws Exception {
		Receive rcv = null;
		try {		
			rcv = em.getReference(Receive.class, rcvId);
			Product prod = null;
			for (ReceiveLine line : lines)
			{
					line.setParentReceive(rcv);
					
					prod = em.merge(line.getProduct());
					line.setProduct(prod);
					
					line = (ReceiveLine)em.merge(line);
					rcv.getRcvLines().add(line);
			}
	
			rcv = update(rcv);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
			
			throw e;
		}	
		
		return rcv;
	}

	@Override
	public Receive process(ASN asn) throws Exception {
		Receive rcv = new Receive();
		rcv = add(rcv);
		asn = em.merge(asn);
		
		//-Assign defaults
		assignRcvCode(rcv, asn.getWarehouse()/*warehouse*/, rcv.getId());
		
		//-Copy basic attrs
		rcv.setWarehouse(asn.getWarehouse());
		
		//-Copy singular attrs
		copySingularAttrs(rcv,asn);
		
		//-Copy plural attrs
		//-- Pickup/Dropoff
		//rcv.setPickUp(copyPickUp(asn.getPickup()));
		//rcv.setDropOff(copyDropOff(asn.getDropOff()));
		
		//-- RcvLine's
		for (ASNLine asnLine : asn.getAsnLines())
		{
			rcv.getRcvLines().add(copyRcvLine(asnLine,rcv));
		}
		
		//Doc Folder
		EntityType et = entityTypeDAOService.provide(em.getMetamodel().entity(Receive.class));
		Folder fldr = documentRepositoryService.provideFolderForEntity(et, rcv.getId());
		fldr = em.merge(fldr);
		rcv.setDocFolder(fldr);
		
		//Note
		Note note = new Note();
		note.setCode(rcv.getCode());
		note = em.merge(note);
		rcv.setNote(note);
		
		rcv = em.merge(rcv);
		
		return rcv;
	}
	
	@Override
	public FileEntry addAttachment(Long rcvId, String sourceFileName, String title, String description, String mimeType, DocType attachmentType) throws Exception {
		Receive rcv = get(rcvId);
		FileEntry fe = documentRepositoryService.addorUpdateFileEntry(rcv,attachmentType,sourceFileName, mimeType, title, description);
		rcv = update(rcv);
		
		return fe;
	}
	
	
	private void copySingularAttrs(Receive rcv, ASN asn) {
		rcv.setDimUnit(asn.getDimUnit());
		rcv.setOuterPackUnit(asn.getOuterPackUnit());
		rcv.setTenant(asn.getTenant());
	}

	private DropOff copyDropOff(ASNDropOff asnDropOff)
	{
		DropOff dro = new DropOff();
		dro = em.merge(dro);
		
		dro.setDockType(asnDropOff.getDockType());
		
		return dro;
	}
	
	private Pickup copyPickUp(ASNPickup asnPickUp)
	{
		Pickup pu = new Pickup();
		pu = em.merge(pu);
		
		pu.setDockType(asnPickUp.getDockType());
		
		return pu;
	}	
	
	
	private ReceiveLine copyRcvLine(ASNLine asnLine, Receive rcv)
	{
		ReceiveLine rl = new ReceiveLine();
		int lineCount = rcv.getRcvLines().size()+1;
		String name = rcv.getName()+"-"+String.format("%02d",lineCount);
		
		rl.setName(name);
		rl.setLineNumber(lineCount);
		rl.setCode(name);
		rl.setStatus(RECEIVELINESTATUS.AWAITING_ARRIVAL);
		
		if (rl.getExpectedInnerPackCount() == null)
			rl.setExpectedInnerPackCount(1);
		
		if (rl.getExpectedOuterPackCount() == null)
			rl.setExpectedOuterPackCount(0);		
		
		if (rl.getExpectedTotalWeight() == null)
			rl.setExpectedTotalWeight(0.0);		
		
		if (rl.getExpectedTotalVolume() == null)
			rl.setExpectedTotalVolume(0.0);	
		
		if (rl.getExpectedTotalLen() == null)
			rl.setExpectedTotalLen(0.0);		
		
		if (rl.getExpectedTotalHeight() == null)
			rl.setExpectedTotalHeight(0.0);	
		
		if (rl.getExpectedTotalWidth() == null)
			rl.setExpectedTotalWidth(0.0);
		rl = em.merge(rl);
		
		rl.setProduct(asnLine.getProduct());
		rl.setParentReceive(rcv);
		
		rl = em.merge(rl);
		
		return rl;
	}		
	
	private void assignRcvCode(Receive newRecord, Warehouse warehouse, Long id) {
		String format = String.format("%%0%dd",6); 
		String paddedId = String.format(format,id);
		String code = "R"+warehouse.getCode()+paddedId;
		newRecord.setName(code);
		newRecord.setCode(code);
	}

	@Override
	public NoteItem addNoteItem(Long rcvId, String content, NoteType noteType)
			throws Exception {
		Receive rcv = get(rcvId);
		NoteItem ni = new NoteItem();
		ni.setParentNote(rcv.getNote());
		ni.setNoteType(noteType);
		ni.setContent(content);
		ni = em.merge(ni);
		
		rcv.getNote().getNotes().add(ni);
		
		rcv = em.merge(rcv);
		
		return ni;
	}
}
