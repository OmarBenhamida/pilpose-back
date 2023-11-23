package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.ChantierEntity;

/**
 * Repository Interface For Pays
 * 
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 15/05/2022
 * @version : 1.0
 */
@Repository
public interface IChantierRepository extends ICommonRepository<ChantierEntity, Long> {

	@Query("SELECT dce FROM ChantierEntity dce")
	ChantierEntity getAll(String username);

	@Query("SELECT dce FROM ChantierEntity dce WHERE dce.reference = :code")
	ChantierEntity getByCode(String code);

	@Query("SELECT dce FROM ChantierEntity dce WHERE dce.client = :client")
	ChantierEntity getUserByClient(String client);

}
