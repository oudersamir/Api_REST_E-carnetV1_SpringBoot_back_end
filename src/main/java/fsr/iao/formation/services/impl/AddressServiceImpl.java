package fsr.iao.formation.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Address;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fsr.iao.formation.entities.AddressEntity;
import fsr.iao.formation.entities.UserEntity;
import fsr.iao.formation.repositories.AddressRepository;
import fsr.iao.formation.repositories.UserRepository;
import fsr.iao.formation.responses.UserResponse;
import fsr.iao.formation.services.AddressService;
import fsr.iao.formation.shared.Utils;
import fsr.iao.formation.shared.dto.AddressDto;
import fsr.iao.formation.shared.dto.UserDto;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils util;

	@Override
	public List<AddressDto> getAllAddresses(String email, int page, int limit) {

		Pageable requestPageable = PageRequest.of(page, limit);
		UserEntity currentUser = userRepository.findByEmail(email);

		List<AddressEntity> ListAddresses = (currentUser.getAdmin() == true) ? addressRepository
				.findAll(requestPageable).getContent() : addressRepository
				.findByUser(currentUser);
		Type listType = new TypeToken<List<AddressDto>>() {
		}.getType();
		List<AddressDto> addressesDto = new ModelMapper().map(ListAddresses,
				listType);
		return addressesDto;
	}

	@Override
	public AddressDto createAddress(AddressDto addressDto, String email) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByEmail(email);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userEntity, UserDto.class);
		addressDto.setUser(userDto);
		addressDto.setAddressId(util.generateStringId(15));
		AddressEntity addressEntity = modelMapper.map(addressDto,
				AddressEntity.class);
		AddressEntity NewAddress = addressRepository.save(addressEntity);
		AddressDto address = modelMapper.map(NewAddress, AddressDto.class);
		return address;
	}

	@Override
	public AddressDto getOnAddress(String addressId) {
		// TODO Auto-generated method stub
		AddressEntity addressEntity=addressRepository.findByAddressId(addressId);
		ModelMapper modelMapper= new ModelMapper();
		AddressDto addressDto= modelMapper.map(addressEntity, AddressDto.class);
		return addressDto;
	}

	@Override
	public void deleteByUserId(String addressId) {
		// TODO Auto-generated method stub
		AddressEntity addressEntity=addressRepository.findByAddressId(addressId);
		if(addressEntity==null) throw new RuntimeException("Address not found");
		addressRepository.deleteByAddressId(addressEntity.getAddressId());
		
	}
	@Override
	public AddressDto updateAddress(String id, AddressDto addressDto) {
		// TODO Auto-generated method stub
        ModelMapper  modelMapper=new ModelMapper();
		
	//	AddressEntity addressEntity =modelMapper.map(addressDto, AddressEntity.class);  
		AddressEntity updateAddress = addressRepository.findByAddressId(id);
		if (updateAddress == null)
			throw new RuntimeException("address updated");
		updateAddress.setCity(addressDto.getCity());
		updateAddress.setStreet(addressDto.getStreet());
		updateAddress.setPostal(addressDto.getPostal());
		updateAddress.setType(addressDto.getType());

		AddressEntity address = addressRepository.save(updateAddress);
        AddressDto addressUpdatedDto=modelMapper.map(address, AddressDto.class);
		
		return addressUpdatedDto;
	}

}
