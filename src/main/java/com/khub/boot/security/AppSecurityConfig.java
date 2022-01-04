package com.khub.boot.security;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.khub.boot.constants.AuthoritiesConstants;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/*
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {		
		//return super.userDetailsService();		
		List<UserDetails> users = new ArrayList<>();
		users.add(User.withDefaultPasswordEncoder().username("nagi").password("12345").roles("USER").build());
		return new InMemoryUserDetailsManager(users);
	}
	*/
	
	//||
	//||
	//\/
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	protected AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        //auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
   
   

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http
//        	.csrf().disable()
//        	.authorizeRequests()
//            .antMatchers("/static/build/app.js", "/static/app/styles/*/**", "/static/app/js/*/**",
//                "/static/build/libs.js", "/index.html", "/static/build/*/**", "/", "/static/**").permitAll()
//            .antMatchers("/auth/**").permitAll()
//            .antMatchers("/api/user/registerClient").permitAll()
//            .antMatchers("/api/user/checklogin/**").permitAll()
//            .antMatchers("/customer/showForm").hasAuthority(AuthoritiesConstants.ADMIN)
//            .antMatchers("/customer/list").hasAnyAuthority(AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN, AuthoritiesConstants.CLIENT)
//            .anyRequest().authenticated()
//            .and()
//        .formLogin()
//        	.loginPage("/login")
//            .loginProcessingUrl("/")
//            .permitAll() 
//			.and()
//		.logout()
//			.permitAll();
		
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/customer/showForm").hasAuthority(AuthoritiesConstants.ADMIN)
			.antMatchers("/customer/list").hasAnyAuthority(AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN, AuthoritiesConstants.CLIENT)
			.antMatchers("/customer/**").hasAnyAuthority(AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN, AuthoritiesConstants.CLIENT)
			.anyRequest().authenticated()
			.and()
			.formLogin();
	}
	
	
}
