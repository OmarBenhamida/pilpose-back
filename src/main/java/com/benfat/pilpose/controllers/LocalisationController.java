/**
 *
 */
package com.benfat.pilpose.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
//@CrossOrigin(origins = { "http://localhost:4200", "https://pilpose.chd-pro.fr", "http://localhost:8100" })
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

	/**
	 * add commune
	 *
	 * @param ClientDto
	 * @return
	 * @throws ParseException
	 */
	@PostMapping(value = ConstantsApplication.REST_PATH_V0)
	public PilposeResponse addClient(@RequestBody LocalisationDto localisationDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add commune controller", null, RsMethodEnum.POST.getValue(),
					"/localisation" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(
				LocalisationDto.entityToDto(localisationService.addOrUpdateLocalisation(localisationDto)),
				HttpStatus.OK);
	}

	/**
	 * update commune
	 *
	 * @param LocalisationDto
	 * @return
	 * @throws ParseException
	 */
	@PutMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse updateClient(@RequestBody LocalisationDto localisationDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"update commune controller", null, RsMethodEnum.PUT.getValue(),
					"/localisation" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(
				LocalisationDto.entityToDto(localisationService.addOrUpdateLocalisation(localisationDto)),
				HttpStatus.OK);
	}

	/**
	 * delete client
	 *
	 * @param clientDto
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/{idLocalisation}", headers = Constants.HEADERS)
	public PilposeResponse deleteClient(@PathVariable Long idLocalisation) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"delete commune controller", null, RsMethodEnum.DELETE.getValue(),
					"/localisation" + ConstantsApplication.REST_PATH_V0, null));
		}

		/** delete commune */
		boolean retour = localisationService.deleteLocalisation(idLocalisation);

		if (retour) {
			return new PilposeResponse(retour, HttpStatus.OK);
		} else {

			return new PilposeResponse(retour, HttpStatus.CONFLICT);
		}
	}

	/**
	 * Generer loader commune
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping(path = ConstantsApplication.REST_PATH_V0 + "/export", headers = Constants.HEADERS)
	public PilposeResponse genererLoaderCommune() throws IOException, ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null, "générer le loader commune",
					null, RsMethodEnum.POST.getValue(), "/v0/export/", null));
		}

		return new PilposeResponse(localisationService.genererLoader(), HttpStatus.OK);
	}

}
