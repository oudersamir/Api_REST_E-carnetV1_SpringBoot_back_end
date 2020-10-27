package fsr.iao.formation.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fsr.iao.formation.entities.AddressEntity;
import fsr.iao.formation.entities.UserEntity;

public interface AddressRepository extends PagingAndSortingRepository<AddressEntity, Long> {
	public List<AddressEntity> findByUser(UserEntity currentUser);
	public AddressEntity findByAddressId(String addressId);
	@Transactional
	@Modifying
	@Query(value="DELETE FROM addresses  WHERE address_id=:addressId ",nativeQuery=true)
	public void deleteByAddressId(@Param("addressId") String addressId);
	
}

