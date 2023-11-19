package com.benfat.pilpose.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.UserEntity;



/**
 * Repository Interface For Pays
 * 
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 15/05/2022
 * @version : 1.0
 */
@Repository
public interface IUserRepository extends ICommonRepository<UserEntity, Long> {
	
	@Query("SELECT dce FROM UserEntity dce WHERE dce.username = :username")
	UserEntity getByUsername(String username);
	
	@Query("SELECT dce FROM UserEntity dce WHERE dce.email = :email")
	UserEntity getByEmail(String email);


	
}
