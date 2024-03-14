package com.benfat.pilpose.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.ChantierEntity;

@Repository
public interface IChantierRepository extends ICommonRepository<ChantierEntity, Long> {

	@Query("SELECT dce FROM ChantierEntity dce")
	ChantierEntity getAll(String username);

	@Query("SELECT dce FROM ChantierEntity dce WHERE dce.reference = :code")
	ChantierEntity getByCode(String code);

	@Query("SELECT dce FROM ChantierEntity dce WHERE dce.client = :client")
	ChantierEntity getUserByClient(String client);

	@Query(value = "select * from chantier WHERE etat = 'En Cours' AND client = :idClient", nativeQuery = true)
	List<ChantierEntity> verifierClientchantierEnCours(@Param("idClient") Long idClient);

	@Query(value = "select * from chantier WHERE etat = 'En Cours' AND localisation = :idLocalisation", nativeQuery = true)
	List<ChantierEntity> verifierCommunechantierEnCours(@Param("idLocalisation") Long idLocalisation);



}
