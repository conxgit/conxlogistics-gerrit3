package com.conx.logistics.kernel.documentlibrary.remote.services;

import java.io.InputStream;
import java.util.List;

import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.mdm.domain.documentlibrary.FileEntry;
import com.conx.logistics.mdm.domain.documentlibrary.Folder;


public interface IRemoteDocumentRepository {
	public void init() throws Exception;
	public Boolean isAvailable() throws Exception;
	
	/**
	 * 
	 * Folder API
	 * 
	 */
	public Folder getFolderById(String folderId)  throws Exception;
	
	public boolean folderExists(String parentFolderId, String name) throws Exception;	
	
	public Folder getFolderByName(String parentFolderId, String folderId)  throws Exception;
	
	public Folder addFolder(String parentFolderId, String name, String description)  throws Exception;
	
	public Folder provideFolderForEntity(EntityType entityType, Long entityId) throws Exception;
	
	public Folder provideFolderForEntity(Class entityJavaClass, Long id) throws Exception;
	
	public void deleteFolderById(String folderId)  throws Exception;
	
	public void deleteFolderByName(String parentFolderId, String folderId)  throws Exception;
	
	/**
	 * 
	 * File API
	 * 
	 */
	public List<FileEntry> getFileEntries(String folderId)  throws Exception;
	
	public FileEntry getFileEntryById(String fileEntryId)  throws Exception;
	
	public FileEntry getFileEntryByTitle(String parentFolderId, String title)  throws Exception;
	
	public boolean fileEntryExists(String parentFolderId, String name) throws Exception;
	
	public FileEntry addFileEntry(String folderId, String sourceFileName, String mimeType, String title, String description, InputStream data, long size) throws Exception;
	
	public FileEntry deleteFileEntryById(String folderId, String fileEntryId)  throws Exception;
	
	/**
	 * 
	 * Props
	 * 
	 * @return
	 */
	public String getConxlogiFolderId();
}
