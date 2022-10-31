package com.bridgelabz.fundoonote.note.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonote.note.dto.NoteDto;
import com.bridgelabz.fundoonote.note.models.Note;
import com.bridgelabz.fundoonote.note.service.INoteService;
import com.bridgelabz.fundoonote.note.utility.NoteResponse;
import com.bridgelabz.fundoonote.user.dto.LoginDto;
import com.bridgelabz.fundoonote.user.dto.RegisterDto;
import com.bridgelabz.fundoonote.user.exception.UserException;
import com.bridgelabz.fundoonote.user.service.IUserService;
import com.bridgelabz.fundoonote.user.utility.UserResponse;

@RestController
@RequestMapping("/home")
@CrossOrigin("*")
public class NoteController {

	@Autowired
	INoteService noteService;
	private String token;

	@PostMapping("/createNote")
	public ResponseEntity<NoteResponse> addNewNote(@RequestBody NoteDto noteDto, @RequestHeader String token) {
		NoteResponse notesEntity = noteService.addNewNote(noteDto, token);
		return new ResponseEntity<NoteResponse>(notesEntity, HttpStatus.OK);
	}

	@GetMapping("/getNotes")
	public ResponseEntity<List<NoteDto>> getNotes() {
		List<NoteDto> response = noteService.getAll(token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/updateNote/{id}")
	public ResponseEntity<NoteResponse> updateNote(@PathVariable(name = "id") Integer noteId,
			@RequestBody NoteDto noteDto) {
		NoteResponse response = noteService.updateNote(noteId, noteDto, token);
		return new ResponseEntity<NoteResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/deleteNote/{id}")
	public ResponseEntity<NoteResponse> deleteNote(@PathVariable Integer id) {
		NoteResponse response = noteService.deleteNote(id);
		return new ResponseEntity<NoteResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/getArchive")
	public ResponseEntity<List<Note>> getArchive(@RequestHeader String token) {
		List<Note> response = noteService.getArchive(token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/archiveandUnarchive/{id}")
	public ResponseEntity<NoteResponse> archiveUnarchiveNote(@RequestHeader String token,
			@PathVariable(name = "id") Integer noteId) {
		NoteResponse response = noteService.archiveAndUnarchive(token, noteId);
		return new ResponseEntity<NoteResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/getTrash")
	public ResponseEntity<List<Note>> getTrash(@RequestHeader String token) {
		List<Note> response = noteService.getTrash(token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/trashandUntrash/{id}")
	public ResponseEntity<NoteResponse> trashAndUntrash(@RequestHeader String token,
			@PathVariable(name = "id") Integer noteId) {
		NoteResponse response = noteService.trashAndUntrash(token, noteId);
		return new ResponseEntity<NoteResponse>(response, HttpStatus.OK);
	}

}
