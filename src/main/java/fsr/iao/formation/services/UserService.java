package fsr.iao.formation.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import fsr.iao.formation.shared.dto.UserDto;

public interface UserService  extends UserDetailsService{
   public UserDto createUser(UserDto userDto);
   public UserDto getUser(String username);
   public UserDto getUserByUserId(String userId);
   public UserDto updateUser(String id,UserDto userDto);
   public void deleteUser(String userId);
   public List<UserDto> getUsers(int page,int limit);
   public List<UserDto> getUsersByCriteria(int page,int limit,String search,int status);

}
	