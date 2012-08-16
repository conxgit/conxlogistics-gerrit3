package com.conx.logistics.mdm.dao.services.impl.documentlibrary;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conx.logistics.common.utils.Validator;
import com.conx.logistics.mdm.dao.services.documentlibrary.IFolderDAOService;
import com.conx.logistics.mdm.domain.documentlibrary.FileEntry;
import com.conx.logistics.mdm.domain.documentlibrary.Folder;

@Transactional
@Repository
public class FolderDAOImpl implements IFolderDAOService {
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
	public Folder get(long id) {
		return em.getReference(Folder.class, id);
	}    

	@Override
	public List<Folder> getAll() {
		return em.createQuery("select o from com.conx.logistics.mdm.domain.documentlibrary.Folder o record by o.id",Folder.class).getResultList();
	}
	
	@Override
	public Folder getByFolderIdOrName(Long folderId,String name) {
		Folder org = null;
		
		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Folder> query = builder.createQuery(Folder.class);
			Root<Folder> rootEntity = query.from(Folder.class);
			ParameterExpression<Long> p1 = builder.parameter(Long.class);
			ParameterExpression<String> p2 = builder.parameter(String.class);
			query.select(rootEntity).where(builder.or(builder.equal(rootEntity.get("folderId"), p1),builder.equal(rootEntity.get("name"), p2)));

			TypedQuery<Folder> typedQuery = em.createQuery(query);
			typedQuery.setParameter(p1, folderId);
			typedQuery.setParameter(p2, name);
			org = typedQuery.getSingleResult();
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
		
		return org;
	}	

	@Override
	public Folder add(Folder record) {
		record = em.merge(record);
		
		return record;
	}

	@Override
	public void delete(Folder record) {
		em.remove(record);
	}

	@Override
	public Folder update(Folder record) {
		return em.merge(record);
	}


	@Override
	public Folder provide(Folder record) {
		Folder existingRecord = getByFolderIdOrName(record.getFolderId(),record.getName());
		if (Validator.isNull(existingRecord))
		{		
			record = update(record);
		}
		return record;
	}
	
	@Override
	public Folder provide(Long folderId, String name) {
		Folder res = null;
		if ((res = getByFolderIdOrName(folderId,name)) == null)
		{
			Folder unit = new Folder();

			unit.setFolderId(folderId);
			unit.setName(name);

			res = add(unit);
		}
		return res;
	}	
	
	@Override
	public void provideDefaults()
	{
	}

	@Override
	public Folder addFileEntry(Long folderId, FileEntry fileEntry) {
		Folder res =  getByFolderIdOrName(folderId,null);
		fileEntry = em.merge(fileEntry);
		res.getFiles().add(fileEntry);
		res = em.merge(res);
		return res;
	}

	@Override
	public Folder addFileEntries(Long folderId, Set<FileEntry> fileEntries) {
		for (FileEntry fe : fileEntries)
		{
			addFileEntry(folderId, fe);
		}
		return getByFolderIdOrName(folderId,null);
	}

	@Override
	public Folder deleteFileEntry(Long folderId, FileEntry fileEntry) {
		Folder res =  getByFolderIdOrName(folderId,null);
		fileEntry = em.merge(fileEntry);
		res.getFiles().remove(fileEntry);
		res = em.merge(res);
		return res;
	}

	@Override
	public Folder deleteFileEntries(Long folderId, Set<FileEntry> fileEntries) {
		for (FileEntry fe : fileEntries)
		{
			deleteFileEntry(folderId, fe);
		}
		return getByFolderIdOrName(folderId,null);
	}
}
