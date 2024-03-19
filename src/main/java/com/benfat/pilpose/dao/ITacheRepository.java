package com.benfat.pilpose.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.TacheEntity;

@Repository
public interface ITacheRepository extends ICommonRepository<TacheEntity, Long> {

	@Query("SELECT dce FROM TacheEntity dce WHERE dce.typeTache = 'tache'")
	List<TacheEntity> getAll();

	@Query(value = "SELECT * FROM tache dce WHERE dce.`id_tache` = ( SELECT MAX(dce2.`id_tache`) FROM tache dce2 )", nativeQuery = true)
	TacheEntity getByAttribute();

	@Query(" SELECT dce FROM TacheEntity dce WHERE dce.idTache =:idTache")
	TacheEntity getByIdTache(Long idTache);

	@Query("SELECT dce FROM TacheEntity dce ORDER BY dce.idTache DESC")
	List<TacheEntity> getLastLineInserted();

}
