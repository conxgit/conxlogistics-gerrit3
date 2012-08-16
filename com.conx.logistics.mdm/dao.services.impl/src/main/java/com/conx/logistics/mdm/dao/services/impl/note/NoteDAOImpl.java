package com.conx.logistics.mdm.dao.services.impl.note;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conx.logistics.mdm.dao.services.note.INoteDAOService;
import com.conx.logistics.mdm.domain.constants.NoteTypeCustomCONSTANTS;
import com.conx.logistics.mdm.domain.note.Note;
import com.conx.logistics.mdm.domain.note.NoteItem;
import com.conx.logistics.mdm.domain.note.NoteType;

@Transactional
@Repository
public class NoteDAOImpl implements INoteDAOService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
    /**
     * Spring will inject a managed JPA {@link EntityManager} into this field.
     */
    @PersistenceContext(unitName="pu")
    private EntityManager em;	
    
    
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Note get(long id) {
		return em.getReference(Note.class, id);
	}    

	@Override
	public List<Note> getAll() {
		return em.createQuery("select o from com.conx.logistics.mdm.domain.note.Note o record order by o.id",Note.class).getResultList();
	}
	


	@Override
	public Note add(Note record) {
		record = em.merge(record);
		
		return record;
	}
	
	public NoteType addNoteType(NoteType record) {
		record = em.merge(record);
		
		return record;
	}	

	@Override
	public void delete(Note record) {
		em.remove(record);
	}

	@Override
	public Note update(Note record) {
		return em.merge(record);
	}

	@Override
	public Note addNoteItem(Long noteId, NoteItem fileEntry) {
		Note res =  get(noteId);
		fileEntry = em.merge(fileEntry);
		res.getNotes().add(fileEntry);
		res = em.merge(res);
		return res;
	}

	@Override
	public Note addNoteItems(Long noteId, Set<NoteItem> noteItems) {
		for (NoteItem fe : noteItems)
		{
			addNoteItem(noteId, fe);
		}
		return get(noteId);
	}

	@Override
	public Note deleteNoteItem(Long noteId, NoteItem fileEntry) {
		Note res =  get(noteId);
		fileEntry = em.merge(fileEntry);
		res.getNotes().remove(fileEntry);
		res = em.merge(res);
		return res;
	}

	@Override
	public Note deleteNoteItems(Long noteId, Set<NoteItem> noteItems) {
		for (NoteItem fe : noteItems)
		{
			deleteNoteItem(noteId, fe);
		}
		return get(noteId);
	}
	
	@Override
	public NoteType getByNoteTypeCode(String code) {
		NoteType res = null;
		
		try
		{
			TypedQuery<NoteType> q = em.createQuery("select o from com.conx.logistics.mdm.domain.note.NoteType o WHERE o.code = :code",NoteType.class);
			q.setParameter("code", code);
						
			res = q.getSingleResult();
		}
		catch(NoResultException e){}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		catch(Error e)
		{
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
		}		
		
		return res;
	}	
	public NoteType provideNoteType(String code, String name) {
		NoteType res = null;
		if ((res = getByNoteTypeCode(code)) == null)
		{
			NoteType unit = new NoteType();

			unit.setCode(code);
			unit.setName(name);

			res = addNoteType(unit);
		}
		return res;
	}	
	
	@Override
	public void provideDefaults()
	{
		provideNoteType(NoteTypeCustomCONSTANTS.TYPE_SPECIAL_INSTRUCTION_CODE, NoteTypeCustomCONSTANTS.TYPE_SPECIAL_INSTRUCTION_NAME);
		provideNoteType(NoteTypeCustomCONSTANTS.TYPE_OTHER_CODE, NoteTypeCustomCONSTANTS.TYPE_OTHER_NAME);	
	}	
}
