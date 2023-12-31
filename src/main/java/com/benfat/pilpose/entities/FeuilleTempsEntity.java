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

/**
 * 
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 01/12/2023
 * @version : 1.0
 */

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

	@Lob
	@Column(name = "panier", columnDefinition = "MEDIUMBLOB", nullable = true)
	private byte[] panier;

	@Column(name = "heure_travaille")
	private int heureTravaille;

	@Column(name = "vehicule")
	private String vehicule;

	@ManyToOne
	@JoinColumn(name = "id_chantier")
	private ChantierEntity idChantier;
	
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

	public byte[] getPanier() {
		return panier;
	}

	public void setPanier(byte[] panier) {
		this.panier = panier;
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
	
	

}
