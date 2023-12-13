package com.benfat.pilpose.service.impl;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.config.JwtTokenProvider;
import com.benfat.pilpose.controllers.dto.UserDto;
import com.benfat.pilpose.dao.ICollaborateurRepository;
import com.benfat.pilpose.dao.IUserRepository;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.entities.UserEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.IUserService;

/**
 * Site service
 * 
 * @inteface ISiteService
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 15/05/2022
 * @version : 1.0
 */
@Service
@Transactional
public class UserService implements IUserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	ICollaborateurRepository collaborateurRepository;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Override
	public UserDto checkUserInfo(UserDto user) throws ParseException {

		if (!user.getUsername().contains("@")) {
			CollaborateurEntity collaborateurEntity = collaborateurRepository.getUserByUsername(user.getUsername());
			//UserEntity userEntity = userRepository.getByUsername(user.getUsername());
			if (collaborateurEntity != null) {

				if (collaborateurEntity.getPassword().equals(user.getPassword())) {
					UserDto userDto = UserDto.collabEntityToUserDto(collaborateurEntity);
					userDto.setToken(jwtTokenProvider.generateToken(userDto));
					return userDto;
				}

				else {
					return null;
				}
			}
		} else {
			CollaborateurEntity collaborateurEntity = collaborateurRepository.getUserByEmail(user.getEmail());
			//UserEntity userEntity = userRepository.getByEmail(user.getEmail());
			if (collaborateurEntity != null) {
				if (collaborateurEntity.getPassword().equals(user.getPassword())) {
					return UserDto.collabEntityToUserDto(collaborateurEntity);
				} else {
					return null;
				}
			}
		}
		return user;

	}

	@Override
	public UserEntity updateUserPassword(UserDto user) throws ParseException {

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), "pilpose updating password", null,
					RsMethodEnum.POST.getValue(), "/auth/v0/updatePassword", null));
		}

		UserEntity userEntity = userRepository.getUserById(user.getIdUser());

		if (userEntity != null) {
			userEntity.setPassword(user.getPassword());
			userRepository.save(userEntity);

		}

		return userEntity;
	}

	@Override
	public UserEntity getUserById(Long idUser) throws ParseException {
		
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), "pilpose get user by id", null,
					RsMethodEnum.POST.getValue(), "/auth/v0/getUserById", null));
		}
		
		UserEntity userEntity = userRepository.getUserById(idUser);
		if (userEntity != null) {
			return userEntity;
		} else {
			return null;
		}
	}
}
