package com.bridgelabz.fundoonote.note.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.note.dto.NoteDto;
import com.bridgelabz.fundoonote.note.exception.NoteException;
import com.bridgelabz.fundoonote.note.models.Note;
import com.bridgelabz.fundoonote.note.utility.NoteResponse;

@Service
public interface INoteService {

	NoteResponse addNewNote(NoteDto noteDto, String token) throws NoteException;

	NoteResponse deleteNote(int id) throws NoteException;
	
	NoteResponse deleteNoteTrash();

	List<NoteDto> getAll(String token);

	NoteResponse updateNote(int id, NoteDto noteDto,String token);

	List<Note> getArchive(String token);

	NoteResponse archiveAndUnarchive(String token, int noteId);

	List<Note> getTrash(String token);

	NoteResponse trashAndUntrash(String token, int noteId);
}

