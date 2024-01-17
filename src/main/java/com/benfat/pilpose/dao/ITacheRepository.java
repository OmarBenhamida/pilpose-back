package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.TacheEntity;

@Repository
public interface ITacheRepository extends ICommonRepository<TacheEntity, Long> {

	@Query("SELECT dce FROM TacheEntity dce")
	TacheEntity getAll();

	@Query(" SELECT dce FROM TacheEntity dce WHERE dce.idTache = (SELECT MAX(dce.idTache) FROM TacheEntity) ")
	TacheEntity getByAttribute();

	@Query(" SELECT dce FROM TacheEntity dce WHERE dce.idTache =:idTache")
	TacheEntity getByIdTache(Long idTache);

}
