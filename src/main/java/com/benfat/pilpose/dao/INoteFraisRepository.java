package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.NoteFraisEntity;

@Repository
public interface INoteFraisRepository extends ICommonRepository<NoteFraisEntity, Long> {

	@Query("SELECT dce FROM NoteFraisEntity dce")
	NoteFraisEntity getAll();

}
