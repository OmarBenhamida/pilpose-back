package com.benfat.pilpose.service;

import java.text.ParseException;

import com.benfat.pilpose.controllers.dto.UserDto;
import com.benfat.pilpose.entities.UserEntity;



/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 05/04/2022
 * @version : 1.0
 */
public interface IUserService {

	/**
	 * sign in 
	 * @return
	 * @throws ParseException 
	 */
	UserDto checkUserInfo(UserDto user) throws ParseException;
	
	/**
	 * update password
	 * @return
	 * @throws ParseException 
	 */
	UserEntity updateUserPassword(UserDto user) throws ParseException;
	
	/**
	 * update password
	 * @return
	 * @throws ParseException 
	 */
	UserEntity getUserById(Long idUser) throws ParseException;


}
