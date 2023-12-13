package com.benfat.pilpose.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.benfat.pilpose.entities.common.AbstractCommonEntity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 01/12/2023
 * @version : 1.0
 */

@Entity
@Table(name = "affectation")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AffectationEntity extends AbstractCommonEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_affectation", nullable = false)
	private Long idAffectation;

	@ManyToOne
	@JoinColumn(name = "id_collaborateur")
	private CollaborateurEntity idCollaborateur;

	@ManyToOne
	@JoinColumn(name = "id_tache")
	private TacheEntity idTache;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdAffectation() {
		return idAffectation;
	}

	public void setIdAffectation(Long idAffectation) {
		this.idAffectation = idAffectation;
	}

	public CollaborateurEntity getIdCollaborateur() {
		return idCollaborateur;
	}

	public void setIdCollaborateur(CollaborateurEntity idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}

	public TacheEntity getIdTache() {
		return idTache;
	}

	public void setIdTache(TacheEntity idTache) {
		this.idTache = idTache;
	}

}
