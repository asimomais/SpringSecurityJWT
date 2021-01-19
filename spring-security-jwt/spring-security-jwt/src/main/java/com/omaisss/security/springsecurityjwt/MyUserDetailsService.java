package com.omaisss.security.springsecurityjwt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	Map<String,String>  users= new HashMap<>();
	
	{
		users.put("asim","123456");
		users.put("moiz","123");
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = null;
		
		if(users.get(userName)!=null) {
			user = new User(userName,users.get(userName), new ArrayList<>() );
			
		}
		
		 
		return user;
	}

	
}


