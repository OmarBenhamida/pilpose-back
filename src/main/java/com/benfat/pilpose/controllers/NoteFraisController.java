/**
 * 
 */
package com.benfat.pilpose.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.benfat.pilpose.controllers.dto.NoteFraisDto;
import com.benfat.pilpose.dao.INoteFraisRepository;
import com.benfat.pilpose.entities.NoteFraisEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.INoteFraisService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/note")
//@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
public class NoteFraisController {

	private static Logger logger = LoggerFactory.getLogger(NoteFraisController.class);

	@Autowired
	INoteFraisService noteFraisService;
	@Autowired
	private ServerProperties serverProperties;

	@Autowired
	INoteFraisRepository noteFraisRepository;

	/**
	 * Get noteFrais Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws IOException 
	 * @throws Exception
	 *
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getAllNoteFrais() throws ParseException, IOException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get all noteFrais controller", null, RsMethodEnum.GET.getValue(),
					"/noteFrais" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = null;
		List<NoteFraisDto> noteFraisDtos = NoteFraisDto.entitiesToDtos(noteFraisService.getAllNoteFrais());
		pilposeResponse = new PilposeResponse(noteFraisDtos, HttpStatus.OK);
		return pilposeResponse;
	}

	/**
	 * add noteFrais
	 * 
	 * @param NoteFraisDto
	 * @return
	 * @throws ParseException
	 * @throws IOException 
	 */
	@PostMapping(value = ConstantsApplication.REST_PATH_V0)
	public PilposeResponse addNoteFrais(@RequestBody NoteFraisDto noteFraisDto) throws ParseException, IOException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"add noteFrais controller", null, RsMethodEnum.POST.getValue(),
					"/noteFrais" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(NoteFraisDto.entityToDto(noteFraisService.addOrUpdateNoteFrais(noteFraisDto)),
				HttpStatus.OK);
	}

	/**
	 * update noteFrais
	 * 
	 * @param NoteFraisDto
	 * @return
	 * @throws ParseException
	 * @throws IOException 
	 */
	@PutMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse updateNoteFrais(@RequestBody NoteFraisDto noteFraisDto) throws ParseException, IOException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"update noteFrais controller", null, RsMethodEnum.PUT.getValue(),
					"/noteFrais" + ConstantsApplication.REST_PATH_V0, null));
		}

		return new PilposeResponse(NoteFraisDto.entityToDto(noteFraisService.addOrUpdateNoteFrais(noteFraisDto)),
				HttpStatus.OK);
	}

	/**
	 * delete noteFrais
	 * 
	 * @param noteFraisDto
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = ConstantsApplication.REST_PATH_V0 + "/{idNoteFrais}", headers = Constants.HEADERS)
	public PilposeResponse deleteNoteFrais(@PathVariable Long idNoteFrais) throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"delete noteFrais controller", null, RsMethodEnum.DELETE.getValue(),
					"/noteFrais" + ConstantsApplication.REST_PATH_V0, null));
		}

		/** delete noteFrais */
		boolean retour = noteFraisService.deleteNoteFrais(idNoteFrais);

		return new PilposeResponse(retour, HttpStatus.OK);
	}

	@PostMapping(value = "/recu/{idC}")
	public String addNoteFrais(@RequestParam("file") MultipartFile file, @PathVariable Long idC) throws ParseException {

		NoteFraisEntity fileEntity = noteFraisRepository.findById(idC).orElse(null);
		try {
			noteFraisService.addNoteWithRecu(fileEntity, file);
		//	fileEntity.setRecu(file.getBytes()); // Convert MultipartFile to byte array

		//	noteFraisRepository.save(fileEntity); // Save to the database

			return "File uploaded successfully";
		} catch (Exception e) {
			return "Error uploading file";
		}
	}

	/**
	 * Generer loader Notes Frais
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping(path = ConstantsApplication.REST_PATH_V0 + "/export", headers = Constants.HEADERS)
	public PilposeResponse genererLoaderNoteFrais() throws IOException, ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), null, "générer le loader notes", null,
					RsMethodEnum.POST.getValue(), "/v0/export/", null));
		}

		return new PilposeResponse(noteFraisService.genererLoader(), HttpStatus.OK);
	}
	
	@GetMapping("/consult/{idNote}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Long idNote) throws IOException {
	NoteFraisEntity entity=	noteFraisRepository.findById(idNote).orElse(null);
	
        // Replace this path with the actual path to your files
        File file = new File(ConstantsApplication.ASSETS.concat(entity.getPathRecu()));

        InputStream inputStream = new FileInputStream(file);

        return ResponseEntity
                .ok()
                .contentLength(file.length())
                .header("Content-Disposition", "attachment; filename="+ConstantsApplication.ASSETS.concat(entity.getPathRecu()))
                .body(new InputStreamResource(inputStream));
    }
	

}
