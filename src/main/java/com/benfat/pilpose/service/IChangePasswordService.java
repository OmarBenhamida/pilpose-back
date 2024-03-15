package com.benfat.pilpose.service;

import com.benfat.pilpose.controllers.dto.ChangePasswordDto;
import com.benfat.pilpose.controllers.dto.ForgotPwdDto;

public interface IChangePasswordService {

	/**
	 * 
	 * @param changeLdapPasswordDto
	 * @return
	 */
	boolean changePasswordLdap(ChangePasswordDto changePasswordDto);

	/**
	 * 
	 * @param changeLdapPasswordDto
	 * @return
	 */
	boolean changePasswordMail(ForgotPwdDto changeLdapPasswordDto);
}
