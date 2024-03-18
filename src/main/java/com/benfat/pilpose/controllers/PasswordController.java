/**
 *
 */
package com.benfat.pilpose.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benfat.pilpose.controllers.dto.ChangePasswordDto;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.IChangePasswordService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/password")
//@CrossOrigin(origins = { "http://localhost:4200", "https://pilpose.chd-pro.fr", "http://localhost:8100" })
public class PasswordController {

	private static Logger LOGGER = LoggerFactory.getLogger(PasswordController.class);

	@Autowired
	private IChangePasswordService changePasswordService;

	/**
	 * Controller pour changer mot de passe compte pilpose
	 *
	 * @return PilposeResponse
	 */
	@PostMapping(value = "/v0", headers = Constants.HEADERS)
	public PilposeResponse changePassord(@RequestBody ChangePasswordDto passwordDto) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(FactoryLog.getRsLog("Pilpose_Backend", null, "Password change", null,
					RsMethodEnum.GET.getValue(), "/v0", null));
		}
		return new PilposeResponse(changePasswordService.changePasswordLdap(passwordDto), HttpStatus.OK);
	}

}
