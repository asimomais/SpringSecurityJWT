package com.omaisss.security.springsecurityjwt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSample {

	@RequestMapping("/sample")
	public String sample() {
		return "sample method in Hello sample"; 
	}
}
