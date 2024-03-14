package com.benfat.pilpose.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.ParametrageEntity;

@Repository
public interface IParametrageRepository extends ICommonRepository<ParametrageEntity, Long> {

	/**
	 *
	 * @param idParametrage
	 * @return
	 */
	ParametrageEntity findByIdParametrage(Long idParametrage);

	/**
	 *
	 * @param code
	 * @return
	 */
	ParametrageEntity findByCode(String code);

	/**
	 *
	 * @param code
	 * @return
	 */
	@Query("SELECT dce FROM ParametrageEntity dce WHERE dce.code = :code")
	ParametrageEntity getDureeMdp(@Param("code") String code);
}
