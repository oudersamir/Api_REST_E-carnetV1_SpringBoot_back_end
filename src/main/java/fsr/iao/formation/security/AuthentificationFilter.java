package fsr.iao.formation.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import fsr.iao.formation.SpringApplicationContext;
import fsr.iao.formation.entities.UserEntity;
import fsr.iao.formation.requests.UserLoginRequest;
import fsr.iao.formation.services.UserService;
import fsr.iao.formation.shared.dto.UserDto;

public class AuthentificationFilter  extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager authenticationManager;
	public AuthentificationFilter(AuthenticationManager authenticationManager){
	this.authenticationManager=authenticationManager;
	}

	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		UserLoginRequest creds;
		try {
			creds = new ObjectMapper().readValue(request.getInputStream(),UserLoginRequest.class);
			return authenticationManager.authenticate
					(new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getPassword(),new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
	
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String userName=((User)auth.getPrincipal()).getUsername();
		UserService userService =(UserService)SpringApplicationContext.getBean("userServiceImpl");
   
	    UserDto userDto = userService.getUser(userName);
		String token=Jwts.builder()
				.setSubject(userName)
				.claim("id", userDto.getUserId())
	            .claim("name", userDto.getFirstName() + " " + userDto.getLastName())
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512,SecurityConstants.TOKEN_SECRET)
				.compact();
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+token);
		response.addHeader("user_id",userService.getUser(userName).getUserId());
        response.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+ userDto.getUserId() + "\"}");

	}
	
	
	
}
