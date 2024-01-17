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
import com.benfat.pilpose.controllers.dto.ClientDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.IClientService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
public class ClientController {

	private static Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	IClientService clientService;
	@Autowired
	private ServerProperties serverProperties;

	/**
	 * Get client Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getAllClient() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get all client controller", null, RsMethodEnum.GET.getValue(),
					"/client" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = null;
		List<ClientDto> clientDtos = ClientDto.entitiesToDtos(clientService.getAllClient());
		pilposeResponse = new PilposeResponse(clientDtos, HttpStatus.OK);
		return pilposeResponse;
	}

	/**
	 * add client
	 * 
	 * @param ClientDto
	 * @return
	 * @throws ParseException
	 */
	@PostMapping(value = ConstantsApplication.REST_PATH_V0)
	public PilposeResponse addClient(@RequestBody ClientDto clientDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add client controller", null, RsMethodEnum.POST.getValue(),
					"/client" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(ClientDto.entityToDto(clientService.addOrUpdateClient(clientDto)), HttpStatus.OK);
	}

	/**
	 * update client
	 * 
	 * @param ClientDto
	 * @return
	 * @throws ParseException
	 */
	@PutMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse updateClient(@RequestBody ClientDto clientDto) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"update client controller", null, RsMethodEnum.PUT.getValue(),
					"/client" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(ClientDto.entityToDto(clientService.addOrUpdateClient(clientDto)), HttpStatus.OK);
	}

	/**
	 * delete client
	 * 
	 * @param clientDto
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/{idClient}", headers = Constants.HEADERS)
	public PilposeResponse deleteClient(@PathVariable Long idClient) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"delete client controller", null, RsMethodEnum.DELETE.getValue(),
					"/client" + ConstantsApplication.REST_PATH_V0, null));
		}

		/** delete client */
		boolean retour = clientService.deleteClient(idClient);

		if (retour) {
			return new PilposeResponse(retour, HttpStatus.OK);
		} else {

			return new PilposeResponse(retour, HttpStatus.CONFLICT);
		}
	}

	/**
	 * Generer loader client
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping(path = ConstantsApplication.REST_PATH_V0 + "/export", headers = Constants.HEADERS)
	public PilposeResponse genererLoaderClient() throws IOException, ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null, "générer le loader client", null,
					RsMethodEnum.POST.getValue(), "/v0/export/", null));
		}

		return new PilposeResponse(clientService.genererLoader(), HttpStatus.OK);
	}

}
