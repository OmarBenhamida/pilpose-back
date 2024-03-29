package com.benfat.pilpose.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benfat.pilpose.dao.common.ICommonRepository;
import com.benfat.pilpose.entities.CollaborateurEntity;

@Repository
public interface ICollaborateurRepository extends ICommonRepository<CollaborateurEntity, Long> {

	@Query("SELECT dce FROM CollaborateurEntity dce")
	List<CollaborateurEntity> getAll();

	@Query("SELECT dce FROM CollaborateurEntity dce WHERE dce.idCollaborateur = :id")
	CollaborateurEntity getUserById(Long id);

	@Query("SELECT dce FROM CollaborateurEntity dce WHERE dce.username = :username")
	CollaborateurEntity getUserByUsername(String username);

	@Query("SELECT dce FROM CollaborateurEntity dce WHERE dce.email = :email")
	CollaborateurEntity getUserByEmail(String email);

	@Query("SELECT dce FROM CollaborateurEntity dce WHERE dce.fonction = :fonction")
	List<CollaborateurEntity> getUserByFonction(String fonction);

}
