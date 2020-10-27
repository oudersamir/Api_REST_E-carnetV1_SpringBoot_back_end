package fsr.iao.formation.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fsr.iao.formation.Exception.UserException;
import fsr.iao.formation.entities.UserEntity;
import fsr.iao.formation.requests.UserRequest;
import fsr.iao.formation.responses.ErrorMessages;
import fsr.iao.formation.responses.UserResponse;
import fsr.iao.formation.services.UserService;
import fsr.iao.formation.shared.dto.UserDto;
/*
 * Users Controller implements all CRUD methods
 */
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@ApiOperation(value = "method to retrieve an user by passing User Id after the path using the verb get")
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> getUser(@PathVariable @ApiParam("User Id (PUBLIC ID)") String id) {
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = userService.getUserByUserId(id);
		UserResponse userResponse = modelMapper
				.map(userDto, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
    @ApiOperation(value=" method to retrieve all users ",notes="if the search param is not null method selected  users by  criteria")
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<UserResponse> getAllUser(
			@RequestParam(value = "page", defaultValue = "0") @ApiParam("page number (pagintion)") int page,
			@RequestParam(value = "limit", defaultValue = "4")@ApiParam("limit number on page (pagintion)") int limit,
			@RequestParam(value = "search", defaultValue = "") @ApiParam("search criteria user (lastname OR firstname")String search,
			@RequestParam(value = "status", defaultValue = "1")@ApiParam("stauts user (activate(1)or not activate(0) ") int status) {
		List<UserResponse> usersResponse = new ArrayList<>();
		List<UserDto> usersDto;
		if (search.equals("")) {
			usersDto = userService.getUsers(page, limit);

		} else {
			usersDto = userService.getUsersByCriteria(page, limit, search,
					status);

		}
		ModelMapper modelMapper = new ModelMapper();
		for (UserDto userDto : usersDto) {
			UserResponse user = modelMapper.map(userDto, UserResponse.class);
			usersResponse.add(user);
		}
		return usersResponse;
	}
    @ApiOperation(value=" methode to create a user")
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> createUser(
			@RequestBody @Valid @ApiParam("user info to persist")
			UserRequest userRequest) throws Exception {
		if (userRequest.getFirstName().isEmpty())
			throw new UserException(
					ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto createUser = userService.createUser(userDto);
		UserResponse userResponse = modelMapper.map(createUser,
				UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse,
				HttpStatus.CREATED);
	}
    @ApiOperation(value=" methode to update a user")
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> updateUser(@PathVariable @ApiParam("user Id (PUBLIC ID") String id,
			@RequestBody @Valid @ApiParam("user info to update") UserRequest userRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto createUser = userService.updateUser(id, userDto);
		UserResponse userResponse = modelMapper.map(createUser,
				UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse,
				HttpStatus.ACCEPTED);
	}
    @ApiOperation(value=" methode to delete a user")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable @ApiParam("user Id (PUBLIC ID)") String id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
