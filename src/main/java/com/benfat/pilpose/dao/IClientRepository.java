package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.ClientEntity;
import com.benfat.pilpose.entities.LocalisationEntity;

/**
 * Repository Interface For Pays
 * 
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 15/05/2022
 * @version : 1.0
 */
@Repository
public interface IClientRepository extends ICommonRepository<ClientEntity, Long> {

	@Query("SELECT dce FROM ClientEntity dce WHERE dce.nom = :nom")
	LocalisationEntity getByNom(String nom);
	
	@Query("SELECT dce FROM ClientEntity dce WHERE dce.idClient = :id")
	ClientEntity getByIdClient(long id);

}
