package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.NoteFraisEntity;

/**
 * Repository Interface For Note Frais
 * 
 * @author : BENHAMIDA OMAR 
 * @creation : 15/11/2023
 * @version : 1.0
 */
@Repository
public interface INoteFraisRepository extends ICommonRepository<NoteFraisEntity, Long> {

	@Query("SELECT dce FROM NoteFraisEntity dce")
	NoteFraisEntity getAll();

}
