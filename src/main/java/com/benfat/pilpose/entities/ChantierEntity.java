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

@Entity
@Table(name = "chantier")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChantierEntity extends AbstractCommonEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_chantier", nullable = false)
	private Long idChantier;

	@Column(name = "reference", length = 20, unique = true, nullable = false)
	private String reference;

	@Column(name = "nom_chantier", nullable = false)
	private String nomChantier;

	@ManyToOne
	@JoinColumn(name = "client")
	private ClientEntity client;

	@Column(name = "etat", nullable = false, length = 50)
	private String etat;

	@ManyToOne
	@JoinColumn(name = "localisation")
	private LocalisationEntity localisation;

	public Long getIdChantier() {
		return idChantier;
	}

	public void setIdChantier(Long idChantier) {
		this.idChantier = idChantier;
	}

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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getNomChantier() {
		return nomChantier;
	}

	public void setNomChantier(String nomChantier) {
		this.nomChantier = nomChantier;
	}

	public LocalisationEntity getLocalisation() {
		return localisation;
	}

	public void setLocalisation(LocalisationEntity localisation) {
		this.localisation = localisation;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}
}
