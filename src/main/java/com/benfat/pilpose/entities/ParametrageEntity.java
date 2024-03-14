package com.benfat.pilpose.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.benfat.pilpose.entities.common.AbstractCommonEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "parametrages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParametrageEntity extends AbstractCommonEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_parametrage", nullable = false)
	private Long idParametrage;

	@Column(name = "libelle")
	private String libelle;

	@Column(name = "valeur")
	private Integer valeur;

	@Column(name = "description")
	private String description;

	@Column(name = "code_p")
	private String code;

	@Override
	public Long getId() {
		return idParametrage;
	}

	public Long getIdParametrage() {
		return idParametrage;
	}

	public void setIdParametrage(Long idParametrage) {
		this.idParametrage = idParametrage;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getValeur() {
		return valeur;
	}

	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
