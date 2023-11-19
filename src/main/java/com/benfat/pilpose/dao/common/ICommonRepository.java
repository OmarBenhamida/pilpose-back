package com.benfat.pilpose.dao.common;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

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
@NoRepositoryBean
public interface ICommonRepository<E, I extends Serializable> extends JpaRepository<E, I>, JpaSpecificationExecutor<E>, PagingAndSortingRepository<E, I> {
}
