package fsr.iao.formation.services;

import java.util.List;

import fsr.iao.formation.shared.dto.AddressDto;
import fsr.iao.formation.shared.dto.UserDto;

public interface AddressService {
   public List<AddressDto> getAllAddresses(String email,int page,int limit);
   public AddressDto createAddress(AddressDto addressDto,String email);
   public AddressDto getOnAddress(String addressId);
   public void deleteByUserId(String addressId);
   public AddressDto updateAddress(String id,AddressDto addressDto);

}
