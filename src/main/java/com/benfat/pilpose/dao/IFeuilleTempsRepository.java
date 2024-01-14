package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.FeuilleTempsEntity;

@Repository
public interface IFeuilleTempsRepository extends ICommonRepository<FeuilleTempsEntity, Long> {

	@Query("SELECT dce FROM FeuilleTempsEntity dce")
	FeuilleTempsEntity getAll();

}
