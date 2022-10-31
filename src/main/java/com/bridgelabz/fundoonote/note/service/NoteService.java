package com.bridgelabz.fundoonote.note.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.note.dto.NoteDto;
import com.bridgelabz.fundoonote.note.exception.NoteException;
import com.bridgelabz.fundoonote.note.models.Note;
import com.bridgelabz.fundoonote.note.repository.NoteRepo;
import com.bridgelabz.fundoonote.note.utility.NoteResponse;
import com.bridgelabz.fundoonote.user.exception.UserException;
import com.bridgelabz.fundoonote.user.models.User;
import com.bridgelabz.fundoonote.user.repository.UserRepo;
import com.bridgelabz.fundoonote.user.utility.EmailSender;
import com.bridgelabz.fundoonote.user.utility.TokenUtil;

@Service
public class NoteService implements INoteService {

	@Autowired
	private NoteRepo noteRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TokenUtil tokenManager;

	@Autowired
	EmailSender emailSender;

	@Autowired
	private UserRepo userRepo;

	private String token;

	@Override
	public NoteResponse addNewNote(NoteDto noteDto, String token) throws NoteException {
		int userId = tokenManager.decodeToken(token);
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		Note note = modelMapper.map(noteDto, Note.class);
		note.setUser(user);
		note = noteRepo.save(note);
		NoteResponse response = new NoteResponse("The response message: ..Note has been added Sucessfully..", 200,
				note);
		return response;
	}

	@Override
	public NoteResponse deleteNote(int noteId) throws NoteException {
		int userId = tokenManager.decodeToken(token);
		userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		Optional<Note> findNote = noteRepo.findById(noteId);
		if (!findNote.isPresent()) {
			throw new NoteException(String.format("Note has already Deleted from the Database !!!"));
		}
		noteRepo.deleteById(noteId);
		NoteResponse response = new NoteResponse("The response message : Note has been deleted Sucessfully !!!", 200,
				noteId);
		return response;
	}

	@Override
	public NoteResponse deleteNoteTrash() {
		noteRepo.findAll().stream().filter(
				note -> note.isTrash() && ChronoUnit.MINUTES.between(note.getTrashTime(), LocalDateTime.now()) > 1L)
				.forEach(note -> noteRepo.delete(note));
		return new NoteResponse("The response message : Note in trash has been deleted Sucessfully !!!", 200, null);
	}

	@Override
	public NoteResponse updateNote(int noteId, NoteDto noteDto, String token) {
		int userId = tokenManager.decodeToken(token);
		userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		Note noteChange = modelMapper.map(noteDto, Note.class);
		noteChange.setNoteid(noteId);
		Note noteUpdate = noteRepo.save(noteChange);
		return new NoteResponse("The response message : Note has been updated Sucessfully", 200, noteUpdate);
	}

	@Override
	public List<NoteDto> getAll(String token) {
		int userId = tokenManager.decodeToken(token);
		userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		List<Note> user = noteRepo.findAll();
		Type userType = new TypeToken<List<NoteDto>>() {
		}.getType();
		List<NoteDto> userDto = modelMapper.map(user, userType);
		return userDto;
	}

	@Override
	public List<Note> getArchive(String token) {
		int userId = tokenManager.decodeToken(token);
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		return user.getNote().stream().filter(note -> note.isArchive()).collect(Collectors.toList());

	}

	@Override
	public NoteResponse archiveAndUnarchive(String token, int noteId) {
		int userId = tokenManager.decodeToken(token);
		userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		noteRepo.findById(noteId).orElseThrow(() -> new NoteException("Note is not present"));
		Note note = noteRepo.findById(noteId).get();
		if (note.isArchive()) {
			note.setArchive(false);
			noteRepo.save(note);
			return new NoteResponse("The response message : Note unarchived Sucessfully", 200, null);
		} else {
			note.setArchive(true);
			noteRepo.save(note);
			return new NoteResponse("The response message : Note Archived Sucessfully", 200, null);
		}

	}

	@Override
	public List<Note> getTrash(String token) {
		int userId = tokenManager.decodeToken(token);
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		return user.getNote().stream().filter(note -> note.isTrash()).collect(Collectors.toList());
	}

	@Override
	public NoteResponse trashAndUntrash(String token, int noteId) {
		int userId = tokenManager.decodeToken(token);
		userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		noteRepo.findById(noteId).orElseThrow(() -> new NoteException("Note is not present"));
		Note note = noteRepo.findById(noteId).get();
		if (note.isTrash()) {
			note.setTrash(false);
			noteRepo.save(note);
			return new NoteResponse("The response message : Note set to UnTrashed Sucessfully", 200, null);
		} else {
			note.setTrash(true);
			note.setTrashTime(LocalDateTime.now());
			noteRepo.save(note);
			return new NoteResponse("The response message : Note set to Trashed Sucessfully", 200, null);
		}

	}

}
