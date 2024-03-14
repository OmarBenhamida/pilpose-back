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
@Table(name = "feuille_temps")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeuilleTempsEntity extends AbstractCommonEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_feuille_temps", nullable = false)
	private Long idFeuilleTemps;

	@Column(name = "reference", nullable = false)
	private String reference;

	@Column(name = "type_travaux", nullable = false)
	private String typeTravaux;

	@Column(name = "jour_semaine")
	private String jourSemaine;

	@Column(name = "heure_travaille")
	private int heureTravaille;

	@Column(name = "vehicule")
	private String vehicule;

	@Column(name = "vehicule_suite")
	private String vehiculeSuite;

	@Column(name = "km")
	private int km;

	@Lob
	@Column(name = "commantaire")
	private String commantaire;

	@ManyToOne
	@JoinColumn(name = "id_chantier")
	private ChantierEntity idChantier;

	@ManyToOne
	@JoinColumn(name = "id_collaborateur")
	private CollaborateurEntity idCollaborateur;

	@ManyToOne
	@JoinColumn(name = "responsable")
	private CollaborateurEntity responsable;

	@Column(name = "statut")
	private String statut;

	@Column(name = "metier")
	private String metier;

	@Column(name = "indemnite")
	private boolean indemnite;

	@Column(name = "validation_chef_equipe")
	private boolean validationChefEquipe;

	@Column(name = "validation_resp_travaux")
	private boolean validationResponsableTravaux;

	@Column(name = "validation_gerant")
	private boolean validationGerant;

	@Column(name = "validation_resp_administratif")
	private boolean validationResponsableAdministratif;



	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdFeuilleTemps() {
		return idFeuilleTemps;
	}

	public void setIdFeuilleTemps(Long idFeuilleTemps) {
		this.idFeuilleTemps = idFeuilleTemps;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getTypeTravaux() {
		return typeTravaux;
	}

	public void setTypeTravaux(String typeTravaux) {
		this.typeTravaux = typeTravaux;
	}

	public String getJourSemaine() {
		return jourSemaine;
	}

	public void setJourSemaine(String jourSemaine) {
		this.jourSemaine = jourSemaine;
	}

	public int getHeureTravaille() {
		return heureTravaille;
	}

	public void setHeureTravaille(int heureTravaille) {
		this.heureTravaille = heureTravaille;
	}

	public String getVehicule() {
		return vehicule;
	}

	public void setVehicule(String vehicule) {
		this.vehicule = vehicule;
	}

	public ChantierEntity getIdChantier() {
		return idChantier;
	}

	public void setIdChantier(ChantierEntity idChantier) {
		this.idChantier = idChantier;
	}

	public CollaborateurEntity getIdCollaborateur() {
		return idCollaborateur;
	}

	public void setIdCollaborateur(CollaborateurEntity idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getCommantaire() {
		return commantaire;
	}

	public void setCommantaire(String commantaire) {
		this.commantaire = commantaire;
	}

	public CollaborateurEntity getResponsable() {
		return responsable;
	}

	public void setResponsable(CollaborateurEntity responsable) {
		this.responsable = responsable;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getVehiculeSuite() {
		return vehiculeSuite;
	}

	public void setVehiculeSuite(String vehiculeSuite) {
		this.vehiculeSuite = vehiculeSuite;
	}

	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}



	public boolean isValidationChefEquipe() {
		return validationChefEquipe;
	}

	public void setValidationChefEquipe(boolean validationChefEquipe) {
		this.validationChefEquipe = validationChefEquipe;
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

	public boolean isValidationResponsableAdministratif() {
		return validationResponsableAdministratif;
	}

	public void setValidationResponsableAdministratif(boolean validationResponsableAdministratif) {
		this.validationResponsableAdministratif = validationResponsableAdministratif;
	}

	public boolean isIndemnite() {
		return indemnite;
	}

	public void setIndemnite(boolean indemnite) {
		this.indemnite = indemnite;
	}




}
