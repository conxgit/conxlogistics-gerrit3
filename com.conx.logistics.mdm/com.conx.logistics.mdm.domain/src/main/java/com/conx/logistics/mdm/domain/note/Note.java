package com.conx.logistics.mdm.domain.note;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.conx.logistics.mdm.domain.MultitenantBaseEntity;
import com.conx.logistics.mdm.domain.metadata.DefaultEntityMetadata;
import com.conx.logistics.mdm.domain.referencenumber.ReferenceNumber;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="mdmnote")
public class Note extends MultitenantBaseEntity {
    @OneToMany(mappedBy="note",cascade = CascadeType.ALL,targetEntity=NoteItem.class)
    private Set<NoteItem> notes = new java.util.HashSet<NoteItem>();
    
    public Note() {
    }

	public Set<NoteItem> getNotes() {
		return notes;
	}

	public void setNotes(Set<NoteItem> notes) {
		this.notes = notes;
	}
}