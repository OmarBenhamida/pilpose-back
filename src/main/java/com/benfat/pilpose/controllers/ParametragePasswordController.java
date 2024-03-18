/**
 *
 */
package com.benfat.pilpose.controllers;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benfat.pilpose.ConstantsApplication;
import com.benfat.pilpose.controllers.dto.ParametrageDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.exception.PilposeTechnicalException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.IParametrageService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/parametrage")
//@CrossOrigin(origins = { "http://localhost:4200", "https://pilpose.chd-pro.fr", "http://localhost:8100" })
public class ParametragePasswordController {

	private static Logger LOGGER = LoggerFactory.getLogger(ParametragePasswordController.class);

	@Autowired
	private IParametrageService parametrageService;

	/**
	 * modifier Parametrage
	 *
	 * @param parametrageDto
	 * @return
	 * @throws PilposeTechnicalException
	 * @throws ParseException
	 */
	@PutMapping(path = ConstantsApplication.REST_PATH_V0 + "/edit", headers = Constants.HEADERS)
	public PilposeResponse editer(@RequestBody List<ParametrageDto> parametrageDtos)
			throws PilposeTechnicalException, ParseException {
		PilposeResponse pilposeResponse = null;
		if (parametrageDtos != null) {
			var parametrageEntities = parametrageService.modifierParametrage(parametrageDtos);
			if (parametrageEntities != null) {
				pilposeResponse = new PilposeResponse(ParametrageDto.entitiesToDtos(parametrageEntities),
						HttpStatus.OK);
			} else {
				/*
				 * an error has been occurred
				 */
				pilposeResponse = new PilposeResponse("error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			/* no sufficient data to do the action */
			pilposeResponse = new PilposeResponse("error", HttpStatus.BAD_REQUEST);
		}
		return pilposeResponse;
	}

	/**
	 * getDemandesByDemandeId
	 *
	 * @param id
	 * @return
	 * @throws PilposeTechnicalException
	 * @throws ParseException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/p/{id}", headers = Constants.HEADERS)
	public PilposeResponse getDemandesByDemandeId(@PathVariable Long id)
			throws PilposeTechnicalException, ParseException {

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null,
					"get  demandes parametrage by id controller", null, RsMethodEnum.POST.getValue(),
					"/demande-depprof/v0/demandes/" + id, null));
		}
		return new PilposeResponse(parametrageService.getById(id), HttpStatus.OK);
	}

	/**
	 * getAll parametrage
	 *
	 * @return
	 * @throws PilposeTechnicalException
	 * @throws ParseException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/p/all", headers = Constants.HEADERS)
	public PilposeResponse getAll() throws PilposeTechnicalException, ParseException {

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null,
					"get  all parametrage controller", null, RsMethodEnum.POST.getValue(), "/parametrage/v0/", null));
		}
		return new PilposeResponse(parametrageService.getAllParametrage(), HttpStatus.OK);
	}

}
