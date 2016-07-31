package com.starter.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// protected final Log log = LogFactory.getLog(getClass());

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// System.out.println("ilk configure içinde");
		// log.info("ilk configure içinde");.
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/").permitAll();//.and().apply(getSpringSocialConfigurer())
		

	}
	


	
}
