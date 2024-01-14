package com.benfat.pilpose.dao.common;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CommonRepositoryImp<E, I extends Serializable> extends SimpleJpaRepository<E, I>
		implements ICommonRepository<E, I> {

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
