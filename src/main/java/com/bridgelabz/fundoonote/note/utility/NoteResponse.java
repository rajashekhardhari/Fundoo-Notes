package com.bridgelabz.fundoonote.note.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {

	private String responseStutus;
	private int resposeCode;
	private Object responseData;

}
