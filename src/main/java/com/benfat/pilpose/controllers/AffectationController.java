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
import com.benfat.pilpose.controllers.dto.AffectationDto;
import com.benfat.pilpose.controllers.dto.TacheDto;
import com.benfat.pilpose.controllers.dto.UpdateAffectationDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.IAffectationService;
import com.benfat.pilpose.service.ITacheService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/affectation")
//@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
public class AffectationController {

	private static Logger logger = LoggerFactory.getLogger(AffectationController.class);

	@Autowired
	IAffectationService affectationService;

	@Autowired
	ITacheService tacheService;

	@Autowired
	private ServerProperties serverProperties;

	/**
	 * Get affectation Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getAllAffectationService() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get all affectation controller", null, RsMethodEnum.GET.getValue(),
					"/affectation" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = null;
		List<AffectationDto> affectationDtos = AffectationDto.entitiesToDtos(affectationService.getAllAffectation());
		pilposeResponse = new PilposeResponse(affectationDtos, HttpStatus.OK);
		return pilposeResponse;
	}

	/**
	 * add affectation
	 * 
	 * @param AffectationDto
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("static-access")
	@PostMapping(value = ConstantsApplication.REST_PATH_V0)
	public PilposeResponse addAffectation(@RequestBody AffectationDto AffectationDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add affectation controller", null, RsMethodEnum.POST.getValue(),
					"/affectation" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(AffectationDto.entityToDto(affectationService.addOrUpdateAffecation(AffectationDto)),
				HttpStatus.OK);
	}

	/**
	 * update affectation
	 * 
	 * @param AffectationDto
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("static-access")
	@PutMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse updateaffectation(@RequestBody AffectationDto AffectationDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"update affectation controller", null, RsMethodEnum.PUT.getValue(),
					"/affectation" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(AffectationDto.entityToDto(affectationService.addOrUpdateAffecation(AffectationDto)),
				HttpStatus.OK);
	}

	/**
	 * delete affectation
	 * 
	 * @param AffectationDto
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/{idAffectation}", headers = Constants.HEADERS)
	public PilposeResponse deleteaffectation(@PathVariable Long idAffectation) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"delete affectation controller", null, RsMethodEnum.DELETE.getValue(),
					"/affectation" + ConstantsApplication.REST_PATH_V0, null));
		}

		/** delete affectation */
		boolean retour = affectationService.deleteAffectation(idAffectation);

		return new PilposeResponse(retour, HttpStatus.OK);
	}

	/**
	 * Generer loader affectation
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping(path = ConstantsApplication.REST_PATH_V0 + "/export", headers = Constants.HEADERS)
	public PilposeResponse genererLoaderAffectation() throws IOException, ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null, "générer le loader affectation",
					null, RsMethodEnum.POST.getValue(), "/v0/export/", null));
		}

		return new PilposeResponse(affectationService.genererLoader(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param listId
	 * @return
	 * @throws ParseException
	 */

	@PostMapping(value = ConstantsApplication.REST_PATH_V0 + "/addList")
	public PilposeResponse addListAffectation(@RequestBody List<Long> listId) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add liste affectation controller", null, RsMethodEnum.POST.getValue(),
					"/affectation" + ConstantsApplication.REST_PATH_V0, null));
		}

		TacheDto tache = tacheService.getTacheByAttribute();

		boolean res = affectationService.addOrUpdateListAffecation(tache, listId);

		if (res == false) {
			return new PilposeResponse(res, HttpStatus.CONFLICT);

		} else {
			return new PilposeResponse(res, HttpStatus.OK);
		}
	}

	/**
	 * 
	 * @param listId
	 * @return
	 * @throws ParseException
	 */

	@PostMapping(value = ConstantsApplication.REST_PATH_V0 + "/updateList")
	public PilposeResponse addListAffectationByidTache(@RequestBody UpdateAffectationDto updateAffectationDto)
			throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add liste affectation controller", null, RsMethodEnum.POST.getValue(),
					"/affectation" + ConstantsApplication.REST_PATH_V0, null));
		}

		TacheDto tache = updateAffectationDto.getTache();

		boolean res = affectationService.updateListAffecation(tache, updateAffectationDto.getListIdsCollab());

		if (res == false) {
			return new PilposeResponse(res, HttpStatus.CONFLICT);

		} else {
			return new PilposeResponse(res, HttpStatus.OK);
		}

	}

	/**
	 * Get salaries by tache
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping(path = ConstantsApplication.REST_PATH_V0 + "/getByIdTache" + "/{idTache}", headers = Constants.HEADERS)
	public PilposeResponse genererSalarieByTache(@PathVariable Long idTache) throws IOException, ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null, "Get salaries by idTache", null,
					RsMethodEnum.POST.getValue(), "/v0/getByIdTache/", null));
		}

		return new PilposeResponse(affectationService.getCollabByIdTache(idTache), HttpStatus.OK);
	}
	
	


}
