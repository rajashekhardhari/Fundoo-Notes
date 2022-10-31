package com.bridgelabz.fundoonote.label.service;

import java.util.List;
import java.util.Optional;
import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.note.exception.NoteException;
import com.bridgelabz.fundoonote.note.models.Note;
import com.bridgelabz.fundoonote.note.repository.NoteRepo;
import com.bridgelabz.fundoonote.label.dto.LabelDto;
import com.bridgelabz.fundoonote.label.models.Label;
import com.bridgelabz.fundoonote.label.repository.LabelRepo;
import com.bridgelabz.fundoonote.label.utility.LabelResponse;
import com.bridgelabz.fundoonote.user.exception.UserException;
import com.bridgelabz.fundoonote.user.models.User;
import com.bridgelabz.fundoonote.user.repository.UserRepo;
import com.bridgelabz.fundoonote.user.utility.EmailSender;
import com.bridgelabz.fundoonote.user.utility.TokenUtil;
@Service
public class LabelService implements ILabelSerivce {

	@Autowired
	private LabelRepo labelRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TokenUtil tokenManager;

	@Autowired
	EmailSender emailSender;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private NoteRepo noteRepo;

	private String token;

	@Override
	public LabelResponse createLabel(LabelDto labelDto, String token) {
		int userId = tokenManager.decodeToken(token);
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		Label label = modelMapper.map(labelDto, Label.class);
		label.setUser(user);
		label = labelRepo.save(label);
		LabelResponse response = new LabelResponse("The response message: ..Label has been created Sucessfully..", 200,
				label);
		return response;
	}

	@Override
	public LabelResponse addLabel(int labelId,int noteId,String token) {
		int userId = tokenManager.decodeToken(token);
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
	    Label label= labelRepo.findByLabelIdAndNoteId(labelId,userId).orElseThrow(() -> new UserException("User not Found"));
		Note note = noteRepo.findById(noteId).orElseThrow(() -> new UserException("Note not Found"));
		note.getLabel().add(label);
		noteRepo.save(note);
		LabelResponse response = new LabelResponse("The response message: ..label has been added Sucessfully..", 200,labelId);
		return response;
	
	}

	@Override
	public LabelResponse updateLabel(int labelId,LabelDto labelDto,String token) {
		int userId = tokenManager.decodeToken(token);
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		Label labelChange = modelMapper.map(labelDto, Label.class);
		labelChange.setLabelId(labelId);
		Label labelUpdate=labelRepo.save(labelChange);
		LabelResponse response = new LabelResponse("The response message: ..label has been updated Sucessfully..", 200,labelUpdate);
		return response;
	
	}

	@Override
	public LabelResponse deleteLabel(int labelId, String token) {
		int userId = tokenManager.decodeToken(token);
		userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		Optional<Label> findLabel = labelRepo.findById(labelId);
		if (!findLabel.isPresent()) {
			throw new NoteException(String.format("Note has already Deleted from the Database !!!"));
		}
		labelRepo.deleteById(labelId);
		LabelResponse response = new LabelResponse("The response message : Note has been deleted Sucessfully !!!", 200,
				labelId);
		return response;
		}

	@Override
	public List<LabelDto> getAll(String token) {
		int userId = tokenManager.decodeToken(token);
		userRepo.findById(userId).orElseThrow(() -> new UserException("User not Found"));
		List<Label> label=labelRepo.findAll();
		Type labelType = new TypeToken<List<LabelDto>>() {
		}.getType();
		List<LabelDto> labelDto = modelMapper.map(label,labelType);
		return labelDto;
	}
}
