package com.conx.logistics.kernel.documentlibrary.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.mdm.domain.MultitenantBaseEntity;

@Entity
@Table(name = "sysdlfileentry")
public class FileEntry extends MultitenantBaseEntity {
	
	@OneToOne
	private Folder folder;
	
	private String _uuid;
	private String _originalUuid;
	private long _fileEntryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userUuid;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private long _versionUserId;
	private String _versionUserUuid;
	private String _versionUserName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _repositoryId;
	private long _folderId;
	private long _originalFolderId;
	private boolean _setOriginalFolderId;
	private String _name;
	private String _originalName;
	private String _extension;
	private String _mimeType;
	private String _originalMimeType;
	private String _title;
	private String _originalTitle;
	private String _description;
	private String _extraSettings;
	private long _fileEntryTypeId;
	private long _originalFileEntryTypeId;
	private boolean _setOriginalFileEntryTypeId;
	private String _version;
	private long _size;
	private int _readCount;
	private long _smallImageId;
	private long _largeImageId;
	private long _custom1ImageId;
	private long _custom2ImageId;
	private long _columnBitmask;
}
