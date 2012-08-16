package com.conx.logistics.mdm.dao.services.note;

import java.util.List;
import java.util.Set;

import com.conx.logistics.mdm.domain.note.Note;
import com.conx.logistics.mdm.domain.note.NoteItem;
import com.conx.logistics.mdm.domain.note.NoteType;

public interface INoteDAOService {
	public Note get(long id);
	
	public List<Note> getAll();

	public Note add(Note record);
	
	public Note addNoteItem(Long noteId, NoteItem noteItem);
	
	public Note addNoteItems(Long noteId, Set<NoteItem> noteItems);

	public void delete(Note record);
	
	public Note deleteNoteItem(Long noteId, NoteItem noteItem);
	
	public Note deleteNoteItems(Long noteId, Set<NoteItem> noteItems);	

	public Note update(Note record);
	
	public void provideDefaults();

	public NoteType getByNoteTypeCode(String code);
}
