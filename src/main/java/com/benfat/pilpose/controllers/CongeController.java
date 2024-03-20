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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.benfat.pilpose.ConstantsApplication;
import com.benfat.pilpose.controllers.dto.CongeDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.ICongeService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/conge")
//@CrossOrigin(origins = { "http://localhost:4200", "https://pilpose.chd-pro.fr", "http://localhost:8100" })
public class CongeController {

	private static Logger logger = LoggerFactory.getLogger(CongeController.class);

	@Autowired
	ICongeService congeService;
	@Autowired
	private ServerProperties serverProperties;

	/**
	 * Get conge Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getAllConge() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get all conge controller", null, RsMethodEnum.GET.getValue(),
					"/conge" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = null;
		List<CongeDto> congeDtos = CongeDto.entitiesToDtos(congeService.getAllConge());
		pilposeResponse = new PilposeResponse(congeDtos, HttpStatus.OK);
		return pilposeResponse;
	}

	/**
	 * add conge
	 *
	 * @param CongeDto
	 * @return
	 * @throws ParseException
	 */
	@PostMapping(value = ConstantsApplication.REST_PATH_V0)
	public PilposeResponse addConge(@RequestBody CongeDto congeDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add conge controller", null, RsMethodEnum.POST.getValue(),
					"/conge" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(CongeDto.entityToDto(congeService.addOrUpdateConge(congeDto)), HttpStatus.OK);
	}

	/**
	 *
	 * @param file
	 * @param idC
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@PostMapping(value = "/excel/{idC}")
	public String addCongeImport(@RequestParam("file") MultipartFile file, @PathVariable Long idC)
			throws ParseException, IOException {
		congeService.addOrUpdateCongesExcel(file, idC);
		return "File uploaded successfully";
	}

	/**
	 * update conge
	 *
	 * @param CongeDto
	 * @return
	 * @throws ParseException
	 */
	@PutMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse updateConge(@RequestBody CongeDto congeDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"update conge controller", null, RsMethodEnum.PUT.getValue(),
					"/conge" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(CongeDto.entityToDto(congeService.addOrUpdateConge(congeDto)), HttpStatus.OK);
	}

	/**
	 * delete conge
	 *
	 * @param congeDto
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/{idConge}", headers = Constants.HEADERS)
	public PilposeResponse deleteConge(@PathVariable Long idConge) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"delete conge controller", null, RsMethodEnum.DELETE.getValue(),
					"/conge" + ConstantsApplication.REST_PATH_V0, null));
		}

		/** delete conge */
		boolean retour = congeService.deleteConge(idConge);

		return new PilposeResponse(retour, HttpStatus.OK);
	}

	/**
	 * Generer loader conge
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping(path = ConstantsApplication.REST_PATH_V0 + "/export", headers = Constants.HEADERS)
	public PilposeResponse genererLoaderConge() throws IOException, ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null, "générer le loader conge", null,
					RsMethodEnum.POST.getValue(), "/v0/export/", null));
		}

		return new PilposeResponse(congeService.genererLoader(), HttpStatus.OK);
	}

}
