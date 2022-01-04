package com.khub.boot.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.khub.boot.model.SAUser;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1401468287810785837L;
	
	//private SAUser saUser; //Not a recommended practice.
	private String userName;
	private String password;
	private boolean isActive;
	private List<GrantedAuthority> authorities;
	
	public CustomUserDetails(SAUser saUser) {
		//super();
		//this.saUser = saUser;
		
		//Recommended practice
		this.userName = saUser.getUsername();
		this.password = saUser.getPassword();
		this.authorities = Arrays.stream(saUser.getRole().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//return Collections.singleton(new SimpleGrantedAuthority(saUser.getRole()));
		return this.authorities;
	}

	@Override
	public String getPassword() {
		//return saUser.getPassword();
		return this.password;
	}

	@Override
	public String getUsername() {
		//return saUser.getUsername();
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
