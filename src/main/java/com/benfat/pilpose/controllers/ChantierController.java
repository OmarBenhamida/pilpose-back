/**
 * 
 */
package com.benfat.pilpose.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
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
import com.benfat.pilpose.controllers.dto.ChantierDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.IChantierService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/chantier")
@CrossOrigin(origins = { "http://localhost:4200", "https://pilpose.chd-pro.fr", "http://localhost:8100" })
public class ChantierController {

	private static Logger logger = LoggerFactory.getLogger(ChantierController.class);

	@Autowired
	IChantierService chantierService;
	@Autowired
	private ServerProperties serverProperties;

	/**
	 * Get chantier Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getAllChantier() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get all chantier controller", null, RsMethodEnum.GET.getValue(),
					"/chantier" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = null;
		List<ChantierDto> chantierDtos = ChantierDto.entitiesToDtos(chantierService.getAllChantier());
		/** Parcourir la liste chantierDtos */
		for (Iterator<ChantierDto> iterator = chantierDtos.iterator(); iterator.hasNext();) {
			ChantierDto chantierDto = iterator.next();

			/** Vérifier Si on trouve le chantier dedier pour congé */
			if (chantierDto.getReference().equals("XXXXXX")) {
				/** Si c'est le cas on supprimer l'objet de la liste */
				iterator.remove();
			}
		}

		pilposeResponse = new PilposeResponse(chantierDtos, HttpStatus.OK);
		return pilposeResponse;
	}

	/**
	 * add chantier
	 * 
	 * @param ChantierDto
	 * @return
	 * @throws ParseException
	 */
	@PostMapping(value = ConstantsApplication.REST_PATH_V0)
	public PilposeResponse addChantier(@RequestBody ChantierDto chantierDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add chantier controller", null, RsMethodEnum.POST.getValue(),
					"/chantier" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(ChantierDto.entityToDto(chantierService.addOrUpdateSite(chantierDto)),
				HttpStatus.OK);
	}

	/**
	 * update chantier
	 * 
	 * @param ChantierDto
	 * @return
	 * @throws ParseException
	 */
	@PutMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse updateChantier(@RequestBody ChantierDto chantierDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"update chantier controller", null, RsMethodEnum.PUT.getValue(),
					"/chantier" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(ChantierDto.entityToDto(chantierService.addOrUpdateSite(chantierDto)),
				HttpStatus.OK);
	}

	/**
	 * delete chantier
	 * 
	 * @param chantierDto
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/{idChantier}", headers = Constants.HEADERS)
	public PilposeResponse deleteChantier(@PathVariable Long idChantier) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"delete chantier controller", null, RsMethodEnum.DELETE.getValue(),
					"/chantier" + ConstantsApplication.REST_PATH_V0, null));
		}

		/** delete chantier */
		boolean retour = chantierService.deleteChantier(idChantier);

		return new PilposeResponse(retour, HttpStatus.OK);
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
	public PilposeResponse genererLoaderChantier() throws IOException, ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null, "générer le loader chantier",
					null, RsMethodEnum.POST.getValue(), "/v0/traitementCTB_NF/", null));
		}

		return new PilposeResponse(chantierService.genererLoader(), HttpStatus.OK);
	}

}

