package com.bridgelabz.fundoonote.label.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonote.label.dto.LabelDto;
import com.bridgelabz.fundoonote.label.service.ILabelSerivce;
import com.bridgelabz.fundoonote.label.utility.LabelResponse;

@RestController
@RequestMapping("/home")
@CrossOrigin("*")
public class LabelController {

	@Autowired
	ILabelSerivce labelService;
	private String token;

	@PostMapping("/createlabel")
	public ResponseEntity<LabelResponse> createLabel(@RequestBody LabelDto labelDto,@RequestHeader("token") String token) {
		LabelResponse labelEntity = labelService.createLabel(labelDto, token);
		return new ResponseEntity<LabelResponse>(labelEntity, HttpStatus.OK);	
	}
	
	@PostMapping("/addlabel")
	public ResponseEntity<LabelResponse> addLabel(@PathVariable int labelId,@PathVariable int noteId ,@RequestHeader("token") String token) {
		LabelResponse labelEntity = labelService.addLabel(labelId,noteId,token);
		return new ResponseEntity<LabelResponse>(labelEntity, HttpStatus.OK);	
	}
	
	@DeleteMapping("/deletelabel")
	public ResponseEntity<LabelResponse> deleteLabel(@RequestHeader int labelId,@RequestParam("token") String token){
		LabelResponse labelEntity = labelService.deleteLabel(labelId,token);
		return new ResponseEntity<LabelResponse>(labelEntity, HttpStatus.OK);
		}
	@GetMapping("/alllabel")
	public ResponseEntity<List<LabelDto>> getAllLabel(@RequestHeader("token") String token)
	{
		List<LabelDto> labelList = labelService.getAll(token);
		return new ResponseEntity<List<LabelDto>>(labelList,HttpStatus.OK);
	}
    
}
