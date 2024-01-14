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
import com.benfat.pilpose.controllers.dto.CollaborateurDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.exception.PilposeTechnicalException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.ICollaborateurService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/collaborateur")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
public class CollaborateurController {

	private static Logger logger = LoggerFactory.getLogger(CollaborateurController.class);

	@Autowired
	ICollaborateurService collaborateurService;
	@Autowired
	private ServerProperties serverProperties;

	/**
	 * Get collaborateur Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getAllCollaborateur() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get all collaborateur controller", null, RsMethodEnum.GET.getValue(),
					"/collaborateur" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = null;
		List<CollaborateurDto> collaborateurDtos = CollaborateurDto
				.entitiesToDtos(collaborateurService.getAllCollaborateur());
		pilposeResponse = new PilposeResponse(collaborateurDtos, HttpStatus.OK);
		return pilposeResponse;
	}

	/**
	 * add collaborateur
	 * 
	 * @param CollaborateurDto
	 * @return
	 * @throws ParseException
	 */
	@PostMapping(value = ConstantsApplication.REST_PATH_V0)
	public PilposeResponse addCollaborateur(@RequestBody CollaborateurDto collaborateurDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add collaborateur controller", null, RsMethodEnum.POST.getValue(),
					"/collaborateur" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(
				CollaborateurDto.entityToDto(collaborateurService.addOrUpdateCollaborateur(collaborateurDto)),
				HttpStatus.OK);
	}

	/**
	 * update collaborateur
	 * 
	 * @param CollaborateurDto
	 * @return
	 * @throws ParseException
	 */
	@PutMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse updateCollaborateur(@RequestBody CollaborateurDto collaborateurDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"update collaborateur controller", null, RsMethodEnum.PUT.getValue(),
					"/collaborateur" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(
				CollaborateurDto.entityToDto(collaborateurService.addOrUpdateCollaborateur(collaborateurDto)),
				HttpStatus.OK);
	}

	/**
	 * delete collaborateur
	 * 
	 * @param collaborateurDto
	 * @return
	 * @throws ParseException
	 * @throws PilposeTechnicalException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/{idCollaborateur}", headers = Constants.HEADERS)
	public PilposeResponse deleteCollaborateur(@PathVariable Long idCollaborateur)
			throws ParseException, PilposeTechnicalException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"delete collaborateur controller", null, RsMethodEnum.DELETE.getValue(),
					"/collaborateur" + ConstantsApplication.REST_PATH_V0, null));
		}

		/** delete collaborateur */
		boolean retour = collaborateurService.deleteCollaborateur(idCollaborateur);

		return new PilposeResponse(retour, HttpStatus.OK);
	}

	/**
	 * get collaborateur by fonction
	 * 
	 * @param collaborateurDto
	 * @return
	 * @throws ParseException
	 * @throws PilposeTechnicalException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/cp", headers = Constants.HEADERS)
	public PilposeResponse getCollaborateurByChefEquipe() throws ParseException, PilposeTechnicalException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get collaborateur by fonction controller", null, RsMethodEnum.GET.getValue(),
					"/collaborateur" + ConstantsApplication.REST_PATH_V0, null));
		}
		/** get collaborateur */
		List<CollaborateurDto> retour = CollaborateurDto
				.entitiesToDtos(collaborateurService.getCollaborateurByfonction("Chef d'equipe"));

		return new PilposeResponse(retour, HttpStatus.OK);
	}

	/**
	 * Generer loader collaborateur
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping(path = ConstantsApplication.REST_PATH_V0 + "/export", headers = Constants.HEADERS)
	public PilposeResponse genererLoaderCollaborateur() throws IOException, ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null, "générer le loader slariés",
					null, RsMethodEnum.POST.getValue(), "/v0/export/", null));
		}

		return new PilposeResponse(collaborateurService.genererLoader(), HttpStatus.OK);
	}

}
