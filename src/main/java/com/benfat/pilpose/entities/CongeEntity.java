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
@Table(name = "conge")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CongeEntity extends AbstractCommonEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conge", nullable = false)
	private Long idConge;

	@Column(name = "reference", nullable = false)
	private String reference;

	@Column(name = "statut", nullable = false)
	private String statut;

	@Column(name = "date_debut")
	private String dateDebut;

	@Column(name = "date_fin")
	private String dateFin;

	@Column(name = "date_depot")
	private String dateDepot;
	
	@Lob
	@Column(name = "commantaire")
	private String commantaire;

	@Column(name = "heure_debut")
	private String heureDebut;

	@Column(name = "heure_fin")
	private String heureFin;

	@Column(name = "validation_resp_travaux")
	private boolean validationResponsableTravaux;
	
	@Column(name = "validation_gerant")
	private boolean validationGerant;
	
	@Column(name = "type_conge")
	private String typeConge;

	@ManyToOne
	@JoinColumn(name = "id_collaborateur")
	private CollaborateurEntity idCollaborateur;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdConge() {
		return idConge;
	}

	public void setIdConge(Long idConge) {
		this.idConge = idConge;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
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

	public String getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(String dateDepot) {
		this.dateDepot = dateDepot;
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

	public String getTypeConge() {
		return typeConge;
	}

	public void setTypeConge(String typeConge) {
		this.typeConge = typeConge;
	}

	public CollaborateurEntity getIdCollaborateur() {
		return idCollaborateur;
	}

	public void setIdCollaborateur(CollaborateurEntity idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}

	public String getCommantaire() {
		return commantaire;
	}

	public void setCommantaire(String commantaire) {
		this.commantaire = commantaire;
	}



	public boolean isValidationResponsableTravaux() {
		return validationResponsableTravaux;
	}

	public void setValidationResponsableTravaux(boolean validationResponsableTravaux) {
		this.validationResponsableTravaux = validationResponsableTravaux;
	}

	public boolean isValidationGerant() {
		return validationGerant;
	}

	public void setValidationGerant(boolean validationGerant) {
		this.validationGerant = validationGerant;
	}




	

}
