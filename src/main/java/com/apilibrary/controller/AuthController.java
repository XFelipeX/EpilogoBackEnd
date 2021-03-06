package com.apilibrary.controller;

import com.apilibrary.model.AuthRequest;
import com.apilibrary.model.User;
import com.apilibrary.repository.UserRepository;
import com.apilibrary.response.AuthResponse;
import com.apilibrary.response.ErrorMessage;
import com.apilibrary.util.JwtUtil;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthController {

	@Autowired
	UserRepository repository;

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private BCryptPasswordEncoder decode;

	@PostMapping("/authenticate")
	public ResponseEntity<Object> generateToken(@RequestBody AuthRequest authRequest) throws Exception {

		decode = new BCryptPasswordEncoder();
		User user = repository.findByEmail(authRequest.getEmail());

		if (user == null || user.getStatus() == 0) {
			ErrorMessage error = new ErrorMessage(new GregorianCalendar(),"", "User not found or is inative");
			return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
		}

		if (!decode.matches(authRequest.getPassword(), user.getUserPassword())) {
			ErrorMessage error = new ErrorMessage(new GregorianCalendar(),"", "Email/Password incorrect");
			return new ResponseEntity<Object>(error, HttpStatus.UNAUTHORIZED);
		}

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), user.getUserPassword()));
		} catch (Exception ex) {
			ErrorMessage error = new ErrorMessage(new GregorianCalendar(),"", "Email/Password incorrect");
			return new ResponseEntity<Object>(error, HttpStatus.UNAUTHORIZED);
		}

		AuthResponse response = new AuthResponse(user,jwtUtil.generateToken(authRequest.getEmail()));

		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}
}