package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.entities.UserEntity;
import com.benfat.pilpose.util.Functions;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUser;

	@Size(max = 20)
	private String username;

	@Size(max = 20)
	private String password;
	
	@Size(max = 20)
	private String nom;
	
	@Size(max = 20)
	private String prenom;
	
	@Size(max = 30)
	private String email;
	
	private String token;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private String dateCreation;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Convert UserEntity -> UserDto
	 * 
	 * @return DeviseDto
	 * @throws ParseException
	 */
	public static UserDto entityToDto(UserEntity entity) throws ParseException {
		UserDto dto = null;
		if (entity != null) {
			dto = new UserDto();
			dto.setIdUser(entity.getIdUser());
			dto.setEmail(entity.getEmail());
			dto.setUsername(entity.getUsername());
			dto.setPassword(entity.getPassword());
			dto.setDateCreation(String.valueOf(entity.getDateCreation()));
		}
		return dto;
	}
	
	/**
	 * Convert UserEntity -> UserDto
	 * 
	 * @return DeviseDto
	 * @throws ParseException
	 */
	public static UserDto collabEntityToUserDto(CollaborateurEntity entity) throws ParseException {
		UserDto dto = null;
		if (entity != null) {
			dto = new UserDto();
			dto.setIdUser(entity.getIdCollaborateur());
			dto.setEmail(entity.getEmail());
			dto.setUsername(entity.getUsername());
			dto.setPassword(entity.getPassword());
			dto.setNom(entity.getNom());
			dto.setPrenom(entity.getPrenom());
		}
		return dto;
	}
 
	/**
	 * Convert SiteDto -> SiteEntity
	 * 
	 * @param UserDto
	 */
	public static UserEntity dtoToEntity(UserDto dto) throws ParseException {
		UserEntity entity = null;
		if (dto != null) {
			entity = new UserEntity();

			entity.setIdUser(dto.getIdUser());
			entity.setEmail(dto.getEmail());
			entity.setUsername(dto.getUsername());
			entity.setPassword(dto.getPassword());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(dto.getDateCreation(), formatter);
			entity.setDateCreation(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}

		return entity;
	}

	/**
	 * Convert list SiteDto -> list SiteEntity
	 * 
	 * @param List<DeviseDto>
	 * @throws ParseException
	 */
	public static List<UserEntity> dtosToEntities(List<UserDto> listDto) throws ParseException {
		List<UserEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (UserDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list SiteEntity -> list SiteDto
	 * 
	 * @param List<SiteEntity>
	 * @throws ParseException
	 */
	public static List<UserDto> entitiesToDtos(List<UserEntity> listEntity) throws ParseException {
		List<UserDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (UserEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
