package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
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
public interface ILocalisationRepository extends ICommonRepository<LocalisationEntity, Long> {

	@Query("SELECT dce FROM LocalisationEntity dce WHERE dce.pays = :pays")
	LocalisationEntity getByPays(String pays);

	@Query("SELECT dce FROM LocalisationEntity dce WHERE dce.ville = :ville")
	LocalisationEntity getByEmail(String ville);

	@Query("SELECT dce FROM LocalisationEntity dce WHERE dce.codePostale = :codePostale")
	LocalisationEntity getByCodePostale(String codePostale);

}
