package com.omaisss.security.springsecurityjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/security")
@RestController
public class HelloResource {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping("/hello")
	public String hello() {
		return "welcome to PMS project";
	}

	@RequestMapping("/hi")
	public String hi() {
		return "say hi to the project with jwt token security";
	}
	
	
	@CrossOrigin
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate( 
			new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword() )
			);
		} catch(BadCredentialsException e) {
			throw new Exception("Incorrect  username or password", e);
		}
		
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		if(userDetails!=null) {
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			
			return ResponseEntity.ok( new AuthenticationResponse(jwt) );
			
		}
		
			
		return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
	
		
		
	}
	
	
}
