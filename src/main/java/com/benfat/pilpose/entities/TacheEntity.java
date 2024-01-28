package com.benfat.pilpose.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.benfat.pilpose.entities.common.AbstractCommonEntity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

	@Column(name = "libelle", nullable = false)
	private String libelle;

	@Column(name = "date_debut")
	private String dateDebut;

	@Column(name = "date_fin")
	private String dateFin;

	@Column(name = "heure_debut")
	private String heureDebut;

	@Column(name = "heure_fin")
	private String heureFin;
	
	@Column(name = "type_tache")
	private String typeTache;

	@Lob
	@Column(name = "commantaire")
	private String commantaire;

	@ManyToOne
	@JoinColumn(name = "id_chantier")
	private ChantierEntity idChantier;

	@ManyToOne
	@JoinColumn(name = "id_collaborateur")
	private CollaborateurEntity responsable;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String TypeTache() {
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

	public CollaborateurEntity getResponsable() {
		return responsable;
	}

	public void setResponsable(CollaborateurEntity responsable) {
		this.responsable = responsable;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public String getTypeTache() {
		return typeTache;
	}

	public void setTypeTache(String typeTache) {
		this.typeTache = typeTache;
	}

	public String getCommantaire() {
		return commantaire;
	}
	
	

}
