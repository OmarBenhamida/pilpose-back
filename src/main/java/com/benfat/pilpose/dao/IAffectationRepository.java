package com.benfat.pilpose.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.AffectationEntity;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.entities.TacheEntity;

@Repository
public interface IAffectationRepository extends ICommonRepository<AffectationEntity, Long> {

	@Query("SELECT dce FROM AffectationEntity dce")
	AffectationEntity getAll();

	@Query("SELECT dce FROM AffectationEntity dce WHERE dce.idTache.typeTache ='tache'")
	List<AffectationEntity> getAllTache();

	@Query("SELECT dce FROM AffectationEntity dce WHERE dce.idCollaborateur.idCollaborateur = :idc")
	List<AffectationEntity> getByIdCollab(Long idc);

	@Query("SELECT DISTINCT dce.idCollaborateur FROM AffectationEntity dce WHERE dce.idTache = :idTache")
	List<CollaborateurEntity> getByIdTache(TacheEntity idTache);


	@Query(value = "DELETE FROM affectation WHERE id_tache = :idTache", nativeQuery = true)
	void deleteAffectationByTache(@Param("idTache") Long idTache);
}
