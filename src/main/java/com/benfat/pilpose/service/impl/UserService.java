package com.benfat.pilpose.service.impl;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.config.JwtTokenProvider;
import com.benfat.pilpose.controllers.dto.UserDto;
import com.benfat.pilpose.dao.IUserRepository;
import com.benfat.pilpose.entities.UserEntity;
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
	JwtTokenProvider jwtTokenProvider;

	@Override
	public UserDto checkUserInfo(UserDto user) throws ParseException {

		if (!user.getUsername().contains("@")) {
			UserEntity userEntity = userRepository.getByUsername(user.getUsername());
			if (userEntity != null) {

				if (userEntity.getPassword().equals(user.getPassword())) {
					UserDto userDto = UserDto.entityToDto(userEntity);
					userDto.setToken(jwtTokenProvider.generateToken(userDto));
					return userDto;
				}

				else {
					return null;
				}
			}
		} else {
			UserEntity userEntity = userRepository.getByEmail(user.getEmail());
			if (userEntity != null) {
				if (userEntity.getPassword().equals(user.getPassword())) {
					return UserDto.entityToDto(userEntity);
				} else {
					return null;
				}
			}
		}
		return user;

	}
}
