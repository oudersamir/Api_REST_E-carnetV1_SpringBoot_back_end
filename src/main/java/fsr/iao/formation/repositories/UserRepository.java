package fsr.iao.formation.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import fsr.iao.formation.entities.UserEntity;
@Repository
public interface UserRepository  extends PagingAndSortingRepository<UserEntity,Long>{
       public UserEntity findByEmail(String email);
       public UserEntity findByUserId(String userId);
         
//       @Query(value=" SELECT * FROM users u WHERE ( u.first_name=?1 OR u.last_name=?1  ) AND u.email_verification_status=?2",nativeQuery=true)
//       public Page<UserEntity> findUserByCriteria(Pageable pageableRequest ,String search,int status);
       
//       @Query(value=" SELECT * FROM users u WHERE ( u.first_name=:search OR u.last_name=:search  ) AND u.email_verification_status=:status",nativeQuery=true)
//       public Page<UserEntity> findUserByCriteria(Pageable pageableRequest ,@Param("search")String search,@Param("status")int status);
       @Query(value=" SELECT * FROM users u WHERE ( u.first_name LIKE %:search% OR u.last_name LIKE %:search%  ) AND u.email_verification_status=:status",nativeQuery=true)
       public Page<UserEntity> findUserByCriteria(Pageable pageableRequest ,@Param("search")String search,@Param("status")int status);
      
}
