/**
 *
 */
package com.benfat.pilpose.controllers;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benfat.pilpose.ConstantsApplication;
import com.benfat.pilpose.controllers.dto.ChantierRecapDto;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.enums.RsMethodEnum;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.response.PilposeResponse;
import com.benfat.pilpose.service.IChantierService;
import com.benfat.pilpose.util.Constants;

@RestController
@RequestMapping("/chantierRecap")
@CrossOrigin(origins = { "http://localhost:4200", "https://pilpose.chd-pro.fr", "http://localhost:8100" })
public class ChantierRecapController {

	private static Logger logger = LoggerFactory.getLogger(ChantierRecapController.class);

	@Autowired
	IChantierService chantierService;
	@Autowired
	private ServerProperties serverProperties;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Get chantier Controller
	 *
	 * @return {PilposeResponse}
	 * @throws ParseException
	 * @throws Exception
	 *
	 */
	@SuppressWarnings("deprecation")
	@GetMapping(value = ConstantsApplication.REST_PATH_V0, headers = Constants.HEADERS)
	public PilposeResponse getAllChantierRecap() throws ParseException {
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getRsLog(OrigineEnum.PILPOSE_AUTH.getValue(), serverProperties.getPort(),
					"get all chantier controller", null, RsMethodEnum.GET.getValue(),
					"/chantier" + ConstantsApplication.REST_PATH_V0, null));
		}

		PilposeResponse pilposeResponse = null;
		String sql = "SELECT id_chantier AS idChantier, SUM(heure_travaille) AS totalHeuresTravaille FROM feuille_temps GROUP BY id_chantier";

		@SuppressWarnings("unchecked")
		List<ChantierRecapDto> result = entityManager.createNativeQuery(sql).unwrap(NativeQuery.class)
				.setResultTransformer(Transformers.aliasToBean(ChantierRecapDto.class)).getResultList();
		pilposeResponse = new PilposeResponse(result, HttpStatus.OK);
		return pilposeResponse;
	}

}
