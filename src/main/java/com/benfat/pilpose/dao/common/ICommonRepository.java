package com.benfat.pilpose.dao.common;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface ICommonRepository<E, I extends Serializable>
		extends JpaRepository<E, I>, JpaSpecificationExecutor<E>, PagingAndSortingRepository<E, I> {
}
