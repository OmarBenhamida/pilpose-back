package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.UserEntity;

@Repository
public interface IUserRepository extends ICommonRepository<UserEntity, Long> {

	@Query("SELECT dce FROM UserEntity dce WHERE dce.username = :username")
	UserEntity getByUsername(String username);

	@Query("SELECT dce FROM UserEntity dce WHERE dce.email = :email")
	UserEntity getByEmail(String email);

	@Query("SELECT dce FROM UserEntity dce WHERE dce.idUser = :idUser")
	UserEntity getUserById(Long idUser);

}
