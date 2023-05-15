package com.jobmii.clientApp.JobmiiClientApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				// .antMatchers("/").hasAnyAuthority("HR", "EMPLOYEE", "CLIENT")
				.antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
				.antMatchers("/login").permitAll()
				.anyRequest()
				.authenticated()
				// .permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.successForwardUrl("/")
				.failureForwardUrl("/login?error=true")
				.permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.permitAll();
	}

}
