package com.benfat.pilpose.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.ConstantsApplication;
import com.benfat.pilpose.controllers.dto.ChangePasswordDto;
import com.benfat.pilpose.controllers.dto.ForgotPwdDto;
import com.benfat.pilpose.dao.ICollaborateurRepository;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.service.IChangePasswordService;

@Service
@Transactional
public class ChangePasswordService implements IChangePasswordService {

	@Autowired
	ICollaborateurRepository collaborateurRepository;

	@Override
	public boolean changePasswordLdap(ChangePasswordDto changeLdapPasswordDto) {
		try {
			CollaborateurEntity salarie = collaborateurRepository
					.getUserByUsername(changeLdapPasswordDto.getUsername());

			if (salarie.getPassword().equals(changeLdapPasswordDto.getOldPassword())) {

				salarie.setPassword(changeLdapPasswordDto.getNewPassword());
				return true;
			} else {

				throw new PilposeBusinessException("Ancien mot de passe incorrect.");
			}

		} catch (NoSuchElementException e) {

			throw new PilposeBusinessException("Utilisateur introuvable.");
		} catch (Exception e) {

			throw new PilposeBusinessException("Une erreur s'est produite lors du changement de mot de passe.");
		}
	}

	@Override
	public boolean changePasswordMail(ForgotPwdDto dto) {
		try {
			CollaborateurEntity salarie = collaborateurRepository.getUserByEmail(dto.getMail());

			salarie.setPassword(ConstantsApplication.DEFAULT_PASSWORD);
			return true;

		} catch (NoSuchElementException e) {

			throw new PilposeBusinessException("Utilisateur introuvable.");
		} catch (Exception e) {

			throw new PilposeBusinessException("Une erreur s'est produite lors du changement de mot de passe.");
		}
	}

}
