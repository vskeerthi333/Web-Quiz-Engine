package engine;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.config.annotation.authentication.builders.*;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceHandler userServiceHandler;

	public SecurityConfiguration() {
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/actuator/shutdown", "/api/register");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin();
		http.headers().frameOptions().sameOrigin();
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceHandler).passwordEncoder(passwordEncoder());
		//auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
	}
}