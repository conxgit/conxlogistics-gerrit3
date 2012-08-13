package com.conx.logistics.kernel.documentlibrary.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.mdm.domain.MultitenantBaseEntity;

@Entity
@Table(name = "sysdlfolder")
public class Folder extends MultitenantBaseEntity {

	@OneToMany(mappedBy="folder")
	private List<FileEntry> files = new ArrayList<FileEntry>();
	
	private String uuid;
	private String originalUuid;
	private long folderId;
	private long groupId;
	private long originalGroupId;
	private boolean setOriginalGroupId;
	private long companyId;
	private long originalCompanyId;
	private boolean setOriginalCompanyId;
	private long userId;
	private String userUuid;
	private String userName;
	private long repositoryId;
	private long originalRepositoryId;
	private boolean setOriginalRepositoryId;
	private boolean mountPoint;
	private boolean originalMountPoint;
	private boolean setOriginalMountPoint;
	private long parentFolderId;
	private long originalParentFolderId;
	private boolean setOriginalParentFolderId;
	private String name;
	private String originalName;
	private String description;
	private Date lastPostDate;
	private long defaultFileEntryTypeId;
	private boolean overrideFileEntryTypes;
	private int status;
	private int originalStatus;
	private boolean setOriginalStatus;
	private long statusByUserId;
	private String statusByUserUuid;
	private String statusByUserName;
	private Date statusDate;
	private String portalUrl;
}
