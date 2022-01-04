package com.khub.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.khub.boot.model.SAUser;
import com.khub.boot.repository.SAUserRepository;
import com.khub.boot.security.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SAUserRepository saUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SAUser user = saUserRepository.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("User Not Found");
		
		return new CustomUserDetails(user);
	}

}
