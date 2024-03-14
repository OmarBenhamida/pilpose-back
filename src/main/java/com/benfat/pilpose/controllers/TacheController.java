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
import com.benfat.pilpose.controllers.dto.TacheDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.ITacheService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/tache")
@CrossOrigin(origins = { "http://localhost:4200", "https://pilpose.chd-pro.fr", "http://localhost:8100" })
public class TacheController {

	private static Logger logger = LoggerFactory.getLogger(TacheController.class);

	@Autowired
	ITacheService tacheService;
	@Autowired
	private ServerProperties serverProperties;

	/**
	 * Get tache Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getAllTache() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get all tache controller", null, RsMethodEnum.GET.getValue(),
					"/tache" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = null;
		List<TacheDto> tacheDtos = TacheDto.entitiesToDtos(tacheService.getAllTache());
		pilposeResponse = new PilposeResponse(tacheDtos, HttpStatus.OK);
		return pilposeResponse;
	}

	/**
	 * add tache
	 *
	 * @param TacheDto
	 * @return
	 * @throws ParseException
	 */
	@PostMapping(value = ConstantsApplication.REST_PATH_V0)
	public PilposeResponse addTache(@RequestBody TacheDto tacheDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add tache controller", null, RsMethodEnum.POST.getValue(),
					"/tache" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(TacheDto.entityToDto(tacheService.addOrUpdateTache(tacheDto)), HttpStatus.OK);
	}

	/**
	 * update tache
	 *
	 * @param TacheDto
	 * @return
	 * @throws ParseException
	 */
	@PutMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse updateTache(@RequestBody TacheDto tacheDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"update tache controller", null, RsMethodEnum.PUT.getValue(),
					"/tache" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(TacheDto.entityToDto(tacheService.addOrUpdateTache(tacheDto)), HttpStatus.OK);
	}

	/**
	 * delete tache
	 *
	 * @param tacheDto
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/{idTache}", headers = Constants.HEADERS)
	public PilposeResponse deleteTache(@PathVariable Long idTache) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"delete tache controller", null, RsMethodEnum.DELETE.getValue(),
					"/tache" + ConstantsApplication.REST_PATH_V0, null));
		}

		/** delete tache */
		boolean retour = tacheService.deleteTache(idTache);

		return new PilposeResponse(retour, HttpStatus.OK);
	}

	/**
	 * Generer loader tache
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping(path = ConstantsApplication.REST_PATH_V0 + "/export", headers = Constants.HEADERS)
	public PilposeResponse genererLoaderTache() throws IOException, ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null, "générer le loader taches", null,
					RsMethodEnum.POST.getValue(), "/v0/export/", null));
		}

		return new PilposeResponse(tacheService.genererLoader(), HttpStatus.OK);
	}

}
