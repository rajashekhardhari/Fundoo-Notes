package com.bridgelabz.fundoonote.note.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.note.dto.NoteDto;
import com.bridgelabz.fundoonote.note.exception.NoteException;
import com.bridgelabz.fundoonote.note.utility.NoteResponse;

@Service
public interface INoteService {

	NoteResponse addNewNote(NoteDto noteDto, String token) throws NoteException;

	NoteResponse deleteNote(int id) throws NoteException;

	List<NoteDto> getAll();

	NoteResponse updateNote(int id, NoteDto noteDto);
}
