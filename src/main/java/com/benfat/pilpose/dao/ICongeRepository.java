package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.CongeEntity;

/**
 * Repository Interface For conge
 * 
 * @author : BENHAMIDA OMAR 
 * @creation : 15/11/2023
 * @version : 1.0
 */
@Repository
public interface ICongeRepository extends ICommonRepository<CongeEntity, Long> {

	@Query("SELECT dce FROM CongeEntity dce")
	CongeEntity getAll();

}
