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
@Table(name = "tache")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TacheEntity extends AbstractCommonEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tache", nullable = false)
	private Long idTache;

	@Column(name = "reference", nullable = false)
	private String reference;

	@Column(name = "libelle", nullable = false)
	private String libelle;

	@Column(name = "date_debut")
	private String dateDebut;

	@Column(name = "date_fin")
	private String dateFin;

	@Column(name = "heure_debut")
	private int heureDebut;

	@Column(name = "heure_fin")
	private int heureFin;

	@Column(name = "commantaire")
	private String commantaire;

	@ManyToOne
	@JoinColumn(name = "id_chantier")
	private ChantierEntity idChantier;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public int getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(int heureDebut) {
		this.heureDebut = heureDebut;
	}

	public int getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(int heureFin) {
		this.heureFin = heureFin;
	}

	public String getCommantaire() {
		return commantaire;
	}

	public void setCommantaire(String commantaire) {
		this.commantaire = commantaire;
	}

	public ChantierEntity getIdChantier() {
		return idChantier;
	}

	public void setIdChantier(ChantierEntity idChantier) {
		this.idChantier = idChantier;
	}

	public void setIdTache(Long idTache) {
		this.idTache = idTache;
	}

	public Long getIdTache() {
		return idTache;
	}

}
