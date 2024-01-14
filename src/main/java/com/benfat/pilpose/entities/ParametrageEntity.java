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
}
