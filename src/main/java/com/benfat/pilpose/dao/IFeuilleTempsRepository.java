package com.benfat.pilpose.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.ChantierEntity;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.entities.FeuilleTempsEntity;

@Repository
public interface IFeuilleTempsRepository extends ICommonRepository<FeuilleTempsEntity, Long> {

	@Query("SELECT dce FROM FeuilleTempsEntity dce")
	FeuilleTempsEntity getAll();

	@Query("SELECT DISTINCT dce.idChantier FROM FeuilleTempsEntity dce")
	List<ChantierEntity> getIdsChantier();

	@Query(value = "SELECT SUM(heure_travaille) FROM feuille_temps where id_chantier = :idChantier", nativeQuery = true)
	int getChantierCout(@Param("idChantier") Long idChantier);
	
	
	@Query("SELECT DISTINCT dce.idCollaborateur FROM FeuilleTempsEntity dce")
	List<CollaborateurEntity> getIdsCollaborateur();
	
	@Query(value = "SELECT SUM(heure_travaille) FROM feuille_temps where id_collaborateur = :idCollaborateur", nativeQuery = true)
	int getCollabCout(@Param("idCollaborateur") Long idCollaborateur);
	
	@Query("SELECT dce FROM FeuilleTempsEntity dce where id_collaborateur = :idCollaborateur")
	List<FeuilleTempsEntity> findByIdCollab(@Param("idCollaborateur") Long idCollaborateur);

}
