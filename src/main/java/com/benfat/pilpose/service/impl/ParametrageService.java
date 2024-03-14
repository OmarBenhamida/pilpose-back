package com.benfat.pilpose.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benfat.pilpose.controllers.dto.ParametrageDto;
import com.benfat.pilpose.dao.IParametrageRepository;
import com.benfat.pilpose.entities.ParametrageEntity;
import com.benfat.pilpose.service.IParametrageService;

@Service
public class ParametrageService implements IParametrageService {

	@Autowired
	private IParametrageRepository parametrageRepository;

	@Override
	public List<ParametrageEntity> modifierParametrage(List<ParametrageDto> parametrageDtos) throws ParseException {
		List<ParametrageEntity> parametrageEntities = ParametrageDto.dtosToEntities(parametrageDtos);

		parametrageEntities.forEach(v -> {
			var obj = parametrageRepository.findByCode(v.getCode());
			if (obj != null) {
				v.setIdParametrage(obj.getId());
			}
		});

		return parametrageEntities.stream().map(v -> parametrageRepository.save(v)).collect(Collectors.toList());
	}

	@Override
	public ParametrageEntity getById(Long id) throws ParseException {

		return parametrageRepository.findByIdParametrage(id);
	}

	@Override
	public List<ParametrageDto> getAllParametrage() throws ParseException {

		List<ParametrageEntity> entitiesP = parametrageRepository.findAll();
		return ParametrageDto.entitiesToDtos(entitiesP);

	}

	@Override
	public ParametrageEntity getDureeMdpByCode(String code) {

		return parametrageRepository.getDureeMdp(code);
	}

}