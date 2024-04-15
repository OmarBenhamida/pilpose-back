package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.CongeEntity;

@Repository
public interface ICongeRepository extends ICommonRepository<CongeEntity, Long> {

	@Query("SELECT dce FROM CongeEntity dce")
	CongeEntity getAll();
	
	@Query(value = "SELECT count(id_conge) FROM conge where statut = :statut", nativeQuery = true)
	int getCongeEnCoursDeValidationCout(@Param("statut") String statut);
	

}
