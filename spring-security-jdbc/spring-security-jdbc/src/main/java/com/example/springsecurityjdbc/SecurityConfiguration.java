package com.example.springsecurityjdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(auth);
		

		/*
		 * auth.inMemoryAuthentication() .withUser("goutam") .password("bazz")
		 * .roles("USER") .and() .withUser("sanu") .password("mcc") .roles("ADMIN");
		 */
		/*
		 * auth.jdbcAuthentication() .dataSource(dataSource) .withDefaultSchema()
		 * .withUser(User.withUsername("goutam").password("bazz").roles("USER"))
		 * .withUser(User.withUsername("sanu").password("mcc").roles("ADMIN"));
		 */
		 auth.jdbcAuthentication() .dataSource(dataSource)
		.usersByUsernameQuery(
				   "select username,password, enabled from users_new where username=?")
				  .authoritiesByUsernameQuery(
				   "select username, authority from authorities_new where username=?");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		
		http.authorizeRequests()
		    .antMatchers("/admin").hasRole("ADMIN")
		    .antMatchers("/user").hasAnyRole("USER","ADMIN")
		    .antMatchers("/fetch").permitAll()
		    .antMatchers("/h2-console/**").permitAll()
		    .and().formLogin();
		 http.csrf().disable();
	        http.headers().frameOptions().disable();
	}
}
