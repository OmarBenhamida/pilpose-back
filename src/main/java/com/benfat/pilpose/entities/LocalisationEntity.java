package com.benfat.pilpose.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.benfat.pilpose.entities.common.AbstractCommonEntity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "localisation")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocalisationEntity extends AbstractCommonEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_localisation", nullable = false)
	private Long idLocalisation;

	@Column(name = "ville", nullable = false)
	private String ville;

	@Column(name = "code_postale", length = 10)
	private String codePostale;

	public Long getIdLocalisation() {
		return idLocalisation;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setIdLocalisation(Long idLocalisation) {
		this.idLocalisation = idLocalisation;
	}

}
