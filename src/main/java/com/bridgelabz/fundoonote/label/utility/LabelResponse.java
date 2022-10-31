package com.bridgelabz.fundoonote.label.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelResponse {

	private String responseStutus;
	private int resposeCode;
	private Object responseData;

}
