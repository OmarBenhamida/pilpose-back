package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.FeuilleTempsEntity;

/**
 * Repository Interface For Feuille Temps
 * 
 * @author : BENHAMIDA OMAR 
 * @creation : 15/11/2023
 * @version : 1.0
 */
@Repository
public interface IFeuilleTempsRepository extends ICommonRepository<FeuilleTempsEntity, Long> {

	@Query("SELECT dce FROM FeuilleTempsEntity dce")
	FeuilleTempsEntity getAll();

}
