package fsr.iao.formation.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

import fsr.iao.formation.requests.AddressRequest;
import fsr.iao.formation.responses.AddressResponse;
import fsr.iao.formation.services.AddressService;
import fsr.iao.formation.shared.dto.AddressDto;
/*
 * Address Controller implements all CRUD methods
 */
@RestController
@RequestMapping("/addresses")
public class AddressController {
	@Autowired
	AddressService addressService;

	@ApiOperation(value = "method to retrieve an address by passing Address Id after the path using the verb get")
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AddressResponse> getOneAddress(
			@PathVariable("id") @ApiParam("User Id (PUBLIC ID)") String addressId) {
		AddressDto addressDto = addressService.getOnAddress(addressId);
		ModelMapper modelMapper = new ModelMapper();
		AddressResponse addressResponse = modelMapper.map(addressDto,
				AddressResponse.class);
		return new ResponseEntity<AddressResponse>(addressResponse,
				HttpStatus.OK);
	}

	@ApiOperation(value = " method to retrieve all addresses relative to user", notes = "if the user is admin all addresses will be selected")
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AddressResponse>> getAllAddresses(
			Principal principal,
			@RequestParam(value = "page", defaultValue = "0") @ApiParam("page number (pagintion)") int page,
			@RequestParam(value = "limit", defaultValue = "20") @ApiParam("limit number on page (pagintion)") int limit) {

		Type listType = new TypeToken<List<AddressResponse>>() {
		}.getType();
		List<AddressResponse> addresses = new ModelMapper().map(addressService
				.getAllAddresses(principal.getName(), page, limit), listType);

		return new ResponseEntity<List<AddressResponse>>(addresses,
				HttpStatus.OK);
	}

	@ApiOperation(value = " methode to create the address relative to user connected")
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AddressResponse> createAddress(
			@RequestBody @Valid @ApiParam("address info to persist") AddressRequest addressRequest,
			@ApiParam("user account") Principal principal) {

		ModelMapper model = new ModelMapper();
		AddressDto addressDto = model.map(addressRequest, AddressDto.class);
		AddressDto address = addressService.createAddress(addressDto,
				principal.getName());
		AddressResponse addressResponse = model.map(address,
				AddressResponse.class);
		return new ResponseEntity<AddressResponse>(addressResponse,
				HttpStatus.CREATED);
	}

	@ApiOperation(value = " methode to delete address by address Id(public id )")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOnAddress(
			@PathVariable("id") @ApiParam("Address Id (PUBLIC ID)") String addressId) {
		addressService.deleteByUserId(addressId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = " methode to update address by address Id(public id )")
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AddressResponse> updateAddress(
			@PathVariable @ApiParam("Address Id (PUBLIC ID)") String id,
			@RequestBody @Valid AddressRequest addressRequest) {
		ModelMapper modelMapper = new ModelMapper();
		AddressDto addressDto = modelMapper.map(addressRequest,
				AddressDto.class);
		AddressDto updateAddress = addressService.updateAddress(id, addressDto);
		AddressResponse addressResponse = modelMapper.map(updateAddress,
				AddressResponse.class);
		return new ResponseEntity<AddressResponse>(addressResponse,
				HttpStatus.ACCEPTED);
	}

}
