package com.benfat.pilpose.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.AffectationEntity;

/**
 * Repository Interface For affectation
 * 
 * @author : BENHAMIDA OMAR 
 * @creation : 15/11/2023
 * @version : 1.0
 */
@Repository
public interface IAffectationRepository extends ICommonRepository<AffectationEntity, Long> {

	@Query("SELECT dce FROM AffectationEntity dce")
	AffectationEntity getAll();

	
	@Query("SELECT dce FROM AffectationEntity dce WHERE dce.idCollaborateur.idCollaborateur = :idc")
	List<AffectationEntity> getByIdCollab(Long idc);
}
