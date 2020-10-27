package fsr.iao.formation.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fsr.iao.formation.services.UserService;
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		// TODO Auto-generated constructor stub
		this.userDetailsService=userDetailsService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
	    .cors().and()
	    .csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
		.permitAll()
		 .antMatchers("/", "/csrf", "/v2/api-docs", 
				 "/swagger-resources/configuration/ui", 
				 "/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security",
				 "/configuration/security", "/swagger-ui.html", "/webjars/**")

		.permitAll()
		.anyRequest().authenticated()
		.and()
 		.addFilter(getAuthenticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	protected AuthentificationFilter getAuthenticationFilter() throws Exception{
		final AuthentificationFilter filter=new AuthentificationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
	
	

}
