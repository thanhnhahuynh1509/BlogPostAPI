package com.htn.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htn.blog.entity.Role;
import com.htn.blog.entity.User;
import com.htn.blog.payload.JwtAuthResponse;
import com.htn.blog.payload.LoginDTO;
import com.htn.blog.payload.SignUpDTO;
import com.htn.blog.repository.UserRepository;
import com.htn.blog.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = tokenProvider.generateToken(authentication);
		
		return new ResponseEntity<>(new JwtAuthResponse(token), HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@RequestBody SignUpDTO signUpDTO) {
		if(userRepository.existsByUsername(signUpDTO.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByEmail(signUpDTO.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setUsername(signUpDTO.getUsername());
		user.setEmail(signUpDTO.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
		user.setName(signUpDTO.getName());
		user.getRoles().add(new Role(1));
		
		userRepository.save(user);
		
		return new ResponseEntity<>("User sign up successfully!.", HttpStatus.OK);
	}
	
}
