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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benfat.pilpose.ConstantsApplication;
import com.benfat.pilpose.controllers.dto.FeuilleTempsDto;
import com.benfat.pilpose.dao.IFeuilleTempsRepository;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.IFeuilleTempsService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/feuille")
//@CrossOrigin(origins = { "http://localhost:4200", "https://pilpose.chd-pro.fr", "http://localhost:8100" })
public class FeuilleTempsController {

	private static Logger logger = LoggerFactory.getLogger(FeuilleTempsController.class);

	@Autowired
	IFeuilleTempsService feuilleTempsService;

	@Autowired
	IFeuilleTempsRepository feuilleTempsRepository;
	@Autowired
	private ServerProperties serverProperties;

	/**
	 * Get feuilleTemps Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getAllFeuilleTemps() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get all feuilleTemps controller", null, RsMethodEnum.GET.getValue(),
					"/feuilleTemps" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = null;
		List<FeuilleTempsDto> feuilleTempsDtos = FeuilleTempsDto.entitiesToDtos(feuilleTempsService.getAllFeuilleTemps());
		pilposeResponse = new PilposeResponse(feuilleTempsDtos, HttpStatus.OK);
		return pilposeResponse;
	}

	/**
	 * add feuilleTemps
	 * 
	 * @param FeuilleTempsDto
	 * @return
	 * @throws ParseException
	 */
	@PostMapping(value = ConstantsApplication.REST_PATH_V0)
	public PilposeResponse addFeuilleTemps(@RequestBody FeuilleTempsDto feuilleTempsDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add feuilleTemps controller", null, RsMethodEnum.POST.getValue(),
					"/feuilleTemps" + ConstantsApplication.REST_PATH_V0, null));
		}
		
		boolean res = feuilleTempsService.addOrUpdateFeuilleTemps(feuilleTempsDto);

		if (!res) {
			return new PilposeResponse(res, HttpStatus.CONFLICT);

		} else {
			return new PilposeResponse(res, HttpStatus.OK);
		}
	}

	/**
	 * update feuilleTemps
	 * 
	 * @param FeuilleTempsDto
	 * @return
	 * @throws ParseException
	 */
	@PutMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse updateFeuilleTemps(@RequestBody FeuilleTempsDto feuilleTempsDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"update feuilleTemps controller", null, RsMethodEnum.PUT.getValue(),
					"/feuilleTemps" + ConstantsApplication.REST_PATH_V0, null));
		}

		boolean res = feuilleTempsService.addOrUpdateFeuilleTemps(feuilleTempsDto);

		if (!res) {
			return new PilposeResponse(res, HttpStatus.CONFLICT);

		} else {
			return new PilposeResponse(res, HttpStatus.OK);
		}
	}

	/**
	 * delete feuilleTemps
	 * 
	 * @param feuilleTempsDto
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/{idFeuilleTemps}", headers = Constants.HEADERS)
	public PilposeResponse deleteFeuilleTemps(@PathVariable Long idFeuilleTemps) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"delete feuilleTemps controller", null, RsMethodEnum.DELETE.getValue(),
					"/feuilleTemps" + ConstantsApplication.REST_PATH_V0, null));
		}

		/** delete feuilleTemps */
		boolean retour = feuilleTempsService.deleteFeuilleTemps(idFeuilleTemps);

		return new PilposeResponse(retour, HttpStatus.OK);
	}

	/**
	 * Get feuilleTemps Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/cht", headers = Constants.HEADERS)
	public PilposeResponse getAllChantierSaisie() throws ParseException {

		PilposeResponse pilposeResponse = null;

		pilposeResponse = new PilposeResponse(feuilleTempsService.getChantierRecaps(), HttpStatus.OK);
		return pilposeResponse;
	}

	/**
	 * Get feuilleTemps Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/slr", headers = Constants.HEADERS)
	public PilposeResponse getAllSalarieSaisie() throws ParseException {

		PilposeResponse pilposeResponse = null;

		pilposeResponse = new PilposeResponse(feuilleTempsService.getCollaborateurRecaps(), HttpStatus.OK);
		return pilposeResponse;
	}
	
	/**
	 * Generer loader chantier
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping(path = ConstantsApplication.REST_PATH_V0 + "/export", headers = Constants.HEADERS)
	public PilposeResponse genererLoaderChantier()
			throws IOException, ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null,
					"générer le loader feuille", null, RsMethodEnum.POST.getValue(),
					"/v0/feuille/", null));
		}


		return new PilposeResponse(feuilleTempsService.genererLoader(), HttpStatus.OK);
	}

}
