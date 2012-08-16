package com.conx.logistics.mdm.dao.services.documentlibrary;

import java.util.List;

import com.conx.logistics.mdm.domain.documentlibrary.FileEntry;

public interface IFileEntryDAOService {
	public FileEntry get(long id);
	
	public List<FileEntry> getAll();
	
	public FileEntry getByCode(String code);	

	public FileEntry add(FileEntry record);

	public void delete(FileEntry record);

	public FileEntry update(FileEntry record);
	
	public FileEntry provide(FileEntry record);
	
	public FileEntry provide(String code, String name);
	
	public void provideDefaults();
}
