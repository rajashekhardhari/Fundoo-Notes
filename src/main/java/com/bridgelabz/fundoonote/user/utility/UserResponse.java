package com.bridgelabz.fundoonote.user.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private String responseStutus;
	private int resposeCode;
	private Object responseData;

}
