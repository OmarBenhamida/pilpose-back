/**
 * 
 */
package com.benfat.pilpose.controllers;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benfat.pilpose.ConstantsApplication;
import com.benfat.pilpose.controllers.dto.LocalisationDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.ILocalisationService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/localisation")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
public class LocalisationController {

	private static Logger logger = LoggerFactory.getLogger(LocalisationController.class);

	@Autowired
	ILocalisationService localisationService;
	@Autowired
	private ServerProperties serverProperties;

	/**
	 * Get localisation Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getAllLocalisation() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get all villes controller", null, RsMethodEnum.GET.getValue(),
					"/affectation" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = null;
		List<LocalisationDto> localisationsDtos = LocalisationDto
				.entitiesToDtos(localisationService.getAllLocalisation());
		pilposeResponse = new PilposeResponse(localisationsDtos, HttpStatus.OK);
		return pilposeResponse;
	}

}
