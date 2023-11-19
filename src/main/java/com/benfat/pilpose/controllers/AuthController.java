/**
 * 
 */
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

import com.benfat.pilpose.controllers.dto.UserDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.IUserService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	private static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	IUserService useService;

	/**
	 * pilpose authentication
	 * 
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	@PostMapping(value = "/v0", headers = Constants.HEADERS)
	public PilposeResponse login(@RequestBody UserDto user) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), "pilpose authentication", null,
					RsMethodEnum.POST.getValue(), "/auth/v0", null));
		}

		var response = useService.checkUserInfo(user);
		System.out.println(response);
		if (response != null && response.getIdUser() !=null) {
			return new PilposeResponse(response, HttpStatus.OK);
		}

		else {
			return new PilposeResponse("user not found", HttpStatus.NOT_FOUND);

		}

	}

}
