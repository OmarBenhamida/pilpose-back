package com.benfat.pilpose.dao.common;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * Common interface spring data repository
 * 
 * @author : ROUAIZI OTHMANE SOFRECOM
 * @see : <othmane.rouaizi@sofrecom.com>
 * @creation : 09/03/2021
 * @version : 1.0
 * 
 * @param <E>
 * @param <I>
 */
public class CommonRepositoryImp<E, I extends Serializable> extends SimpleJpaRepository<E, I> implements ICommonRepository<E, I> {

	@Autowired
	EntityManager entityManager;

	/**
	 * Implementation repository
	 * 
	 * @param domainClass
	 * @param entityManager
	 */
	public CommonRepositoryImp(Class<E> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}
}
