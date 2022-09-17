package com.bridgelabz.fundoonote.user.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;

@Component
public class TokenUtil {
	
	@Value("${secret}")
	private String jwtSecret;

	public String encodeToken(int id) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

			String token = JWT.create().withClaim("user_id", id).sign(algorithm);
			System.out.println("Encoded Token is : " + token);
			return token;
		} catch (JWTCreationException exception) {
			exception.printStackTrace();
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public int decodeToken(String token) {
		Integer userid;
		Verification verification = null;

		Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

		try {
			verification = JWT.require(algorithm);
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
		}
		JWTVerifier jwtVerifier = verification.build();

		DecodedJWT decodedJWT = jwtVerifier.verify(token);
		Claim claim = decodedJWT.getClaim("user_id");
		userid = claim.asInt();
		return userid;
	}
}
