package com.benfat.pilpose.controllers;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benfat.pilpose.ConstantsApplication;
import com.benfat.pilpose.controllers.dto.ForgotPwdDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.EmailService;
import com.benfat.pilpose.service.IChangePasswordService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/forgotpwd")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
public class ForgotPwdController {

	private static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	IChangePasswordService changePasswordService;

	@Autowired
	private EmailService emailService;

	@PostMapping(value = "/v0", headers = Constants.HEADERS)
	public PilposeResponse forgotPwd(@RequestBody ForgotPwdDto user) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), "pilpose change pwd", null,
					RsMethodEnum.POST.getValue(), "/forgotpwd/v0", null));
		}
		Boolean result = changePasswordService.changePasswordMail(user);
		if (result) {
			return new PilposeResponse(emailService.sendEmail(user.getMail(), "Pilpose - Mot de passe oublié ",
					"votre nouveau mote de passe est : " + ConstantsApplication.DEFAULT_PASSWORD
							+ "\n veuillez le modifier aprés la première connexion"),
					HttpStatus.OK);
		} else {
			return new PilposeResponse("Une erreur s'est produite lors du changement de mot de passe.",
					HttpStatus.BAD_REQUEST);
		}

	}

}
