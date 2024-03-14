package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.ClientEntity;
import com.benfat.pilpose.entities.LocalisationEntity;

@Repository
public interface IClientRepository extends ICommonRepository<ClientEntity, Long> {

	@Query("SELECT dce FROM ClientEntity dce WHERE dce.nom = :nom")
	LocalisationEntity getByNom(String nom);

	@Query("SELECT dce FROM ClientEntity dce WHERE dce.idClient = :id")
	ClientEntity getByIdClient(long id);

}
