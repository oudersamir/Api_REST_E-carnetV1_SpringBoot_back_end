package fsr.iao.formation.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fsr.iao.formation.entities.UserEntity;
import fsr.iao.formation.repositories.UserRepository;
import fsr.iao.formation.services.UserService;
import fsr.iao.formation.shared.Utils;
import fsr.iao.formation.shared.dto.AddressDto;
import fsr.iao.formation.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils util;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub

		UserEntity checkUser = userRepository.findByEmail(userDto.getEmail());
		if (checkUser != null)
			throw new RuntimeException("user already exist");

		ModelMapper modelMapper = new ModelMapper();

		for (int i = 0; i < userDto.getAddresses().size(); i++) {
			AddressDto addressDto = userDto.getAddresses().get(i);
			addressDto.setUser(userDto);
			addressDto.setAddressId(util.generateStringId(30));
			userDto.getAddresses().set(i, addressDto);
		}
		userDto.getContact().setContactId(util.generateStringId(30));
		userDto.getContact().setUser(userDto);
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

		userEntity.setEncryptePassword(bCryptPasswordEncoder.encode(userEntity
				.getPassword()));
		userEntity.setUserId(util.generateStringId(15));
		UserEntity user = userRepository.save(userEntity);

		UserDto userCreatedDto = modelMapper.map(user, UserDto.class);

		return userCreatedDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(),
				userEntity.getEncryptePassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String username) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findByEmail(username);
		if (userEntity == null)
			throw new RuntimeException(username);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new RuntimeException(userId);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		// TODO Auto-generated method stub

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);

		UserEntity updateUser = userRepository.findByUserId(id);
		if (updateUser == null)
			throw new RuntimeException("user updated");
		updateUser.setLastName(userDto.getLastName());
		updateUser.setFirstName(userDto.getFirstName());
		UserEntity user = userRepository.save(updateUser);

		UserDto userUpdatedDto = new UserDto();
		BeanUtils.copyProperties(user, userUpdatedDto);
		return userUpdatedDto;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		UserEntity user = userRepository.findByUserId(userId);
		if (user == null)
			new RuntimeException();
		userRepository.delete(user);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		// TODO Auto-generated method stub
		List<UserDto> usersDto = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> pageUsers = userRepository.findAll(pageableRequest);
		List<UserEntity> usersEntity = pageUsers.getContent();
		ModelMapper modelMapper = new ModelMapper();
		for (UserEntity userEntity : usersEntity) {
			UserDto userDto = modelMapper.map(userEntity, UserDto.class);
			usersDto.add(userDto);
		}
		return usersDto;
	}

	@Override
	public List<UserDto> getUsersByCriteria(int page, int limit, String search,int status) {
		List<UserDto> usersDto = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> pageUsers = userRepository.findUserByCriteria(pageableRequest, search,status);
		List<UserEntity> usersEntity = pageUsers.getContent();
		ModelMapper modelMapper = new ModelMapper();
		for (UserEntity userEntity : usersEntity) {
			UserDto userDto = modelMapper.map(userEntity, UserDto.class);
			usersDto.add(userDto);
		}
		return usersDto;
	}



}
