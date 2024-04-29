package com.benfat.pilpose.controllers;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benfat.pilpose.ConstantsApplication;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.ICollaborateurService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/planning")
//@CrossOrigin(origins = { "http://localhost:4200", "https://pilpose.chd-pro.fr", "http://localhost:8100" })
public class PlanningController {
	@Autowired
	private ServerProperties serverProperties;

	@Autowired
	ICollaborateurService collaborateurService;

	private static Logger logger = LoggerFactory.getLogger(AffectationController.class);

	/**
	 * Get affectation Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/{idC}", headers = Constants.HEADERS)
	public PilposeResponse getPlanningById(@PathVariable Long idC) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get planning controller", null, RsMethodEnum.GET.getValue(),
					"/planning" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = new PilposeResponse(collaborateurService.getPlanningById(idC), HttpStatus.OK);

		return pilposeResponse;
	}

	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getPlanningAll() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get planning controller", null, RsMethodEnum.GET.getValue(),
					"/planning" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = new PilposeResponse(collaborateurService.getPlanningAll(), HttpStatus.OK);

		return pilposeResponse;
	}
	
	/**
	 * Get affectation Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V1 + "/{idC}", headers = Constants.HEADERS)
	public PilposeResponse getPlanningByIdFiltred(@PathVariable Long idC) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get planning controller", null, RsMethodEnum.GET.getValue(),
					"/planning" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = new PilposeResponse(collaborateurService.getPlanningByIdFiltred(idC), HttpStatus.OK);

		return pilposeResponse;
	}

	@GetMapping(value = ConstantsApplication.REST_PATH_V1, headers = Constants.HEADERS)
	public PilposeResponse getPlanningAllFiltred() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get planning controller", null, RsMethodEnum.GET.getValue(),
					"/planning" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = new PilposeResponse(collaborateurService.getPlanningAllFiltred(), HttpStatus.OK);

		return pilposeResponse;
	}


}
