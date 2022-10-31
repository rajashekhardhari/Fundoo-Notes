package com.bridgelabz.fundoonote.label.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.label.dto.LabelDto;
import com.bridgelabz.fundoonote.label.utility.LabelResponse;
@Service
public interface ILabelSerivce {
	
	LabelResponse createLabel(LabelDto labelto,String token);		
	
	LabelResponse addLabel(int labelId,int noteId,String token);
	
	LabelResponse updateLabel(int labelId,LabelDto labelDto,String token);
	
	LabelResponse deleteLabel(int labelid,String token);
	
	List<LabelDto> getAll(String token);
}
