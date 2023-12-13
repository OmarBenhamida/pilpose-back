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
 * @creation : 15/05/2022
 * @version : 1.0
 */

@Entity
@Table(name = "note_frais")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoteFraisEntity extends AbstractCommonEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_note_frais", nullable = false)
	private Long idNoteFrais;

	@Column(name = "reference", length = 20, nullable = false)
	private String reference;

	@Column(name = "type_note", nullable = false)
	private String typeNote;

	@Column(name = "date_note")
	private String dateNote;

	@Lob
	@Column(name = "recu", columnDefinition = "MEDIUMBLOB", nullable = true)
	private byte[] recu;
	
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Long getIdNoteFrais() {
		return idNoteFrais;
	}

	public void setIdNoteFrais(Long idNoteFrais) {
		this.idNoteFrais = idNoteFrais;
	}

	public String getTypeNote() {
		return typeNote;
	}

	public void setTypeNote(String typeNote) {
		this.typeNote = typeNote;
	}

	public String getDateNote() {
		return dateNote;
	}

	public void setDateNote(String dateNote) {
		this.dateNote = dateNote;
	}

	public byte[] getRecu() {
		return recu;
	}

	public void setRecu(byte[] recu) {
		this.recu = recu;
	}

	public CollaborateurEntity getIdCollaborateur() {
		return idCollaborateur;
	}

	public void setIdCollaborateur(CollaborateurEntity idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}
	
	

}
