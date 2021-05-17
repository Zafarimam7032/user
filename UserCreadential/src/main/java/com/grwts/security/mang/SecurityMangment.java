package com.grwts.security.mang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.grwts.permission.UserPermission;
import com.grwts.security.user.service.UserRoleService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityMangment extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserPermission userPermission;
	@Autowired
	private UserRoleService userRoleService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/home/user/**").permitAll()
		.and()
		.authorizeRequests()
		.anyRequest().authenticated().and().httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**");
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails userDetails = User.builder().username("admin").password(passwordEncoder.encode("1234")).roles("admin")
				//.authorities(userPermission.getGrantedAuthorities("admin"))
				.build();
	UserDetails userZafar = User.builder().username("zafar").password(passwordEncoder.encode("1234")).roles("USER").build();
		return new InMemoryUserDetailsManager(userDetails,userZafar);
	}
	

}
