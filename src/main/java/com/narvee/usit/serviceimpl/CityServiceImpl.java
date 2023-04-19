package com.narvee.usit.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.City;
import com.narvee.usit.repository.ICityRepository;
import com.narvee.usit.service.ICityService;

@Service
public class CityServiceImpl implements ICityService {

	private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	@Autowired
	private ICityRepository repository;

	@Override
	public City saveCity(City city) {
		logger.info("!!! inside class: CityServiceImpl, !! method: saveCity");
		return repository.save(city);

	}

	@Override
	public List<City> getAllCity() {
		logger.info("!!! inside class: CityServiceImpl, !! method: getAllCity");
		return repository.findAll();
	}

	@Override
	public void deletecityByID(long id) {
		logger.info("!!! inside class: CityServiceImpl, !! method: deletecityByID");
		City city = repository.findById(id).get();
		repository.delete(city);
	}

	@Override
	public City getCityByID(long id) {
		logger.info("!!! inside class: CityServiceImpl, !! method: getCityByID");
		return repository.findById(id).get();
	}

	@Override
	public List<Object[]> getcities() {
		return repository.getcities();
	}

}
