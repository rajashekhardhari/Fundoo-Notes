package com.bridgelabz.fundoonote.note.service;

import java.util.List;
import java.util.Optional;
import java.lang.reflect.Type;
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

	@Override
	public NoteResponse addNewNote(NoteDto noteDto, String token) throws NoteException {
		int userId = tokenManager.decodeToken(token);
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		Note note = modelMapper.map(noteDto, Note.class);
        user.getNote().add(note);
		user = userRepo.save(user);
		NoteResponse response = new NoteResponse("The response message: ..Note has been added Sucessfully..", 200,
				note);
		return response;
	}

	@Override
	public NoteResponse deleteNote(int noteId) throws NoteException {
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
	public NoteResponse updateNote(int noteId, NoteDto noteDto) {
		Note noteChange = modelMapper.map(noteDto, Note.class);
		noteChange.setNoteid(noteId);

		Note noteUpdate = noteRepo.save(noteChange);
		NoteResponse response = new NoteResponse("The response message : Note has been updated Sucessfully", 200,
				noteUpdate);
		return response;
	}

	@Override
	public List<NoteDto> getAll() {
		List<Note> user = noteRepo.findAll();
		Type userType = new TypeToken<List<NoteDto>>() {

		}.getType();
		List<NoteDto> userDto = modelMapper.map(user, userType);
		return userDto;
	}

}
