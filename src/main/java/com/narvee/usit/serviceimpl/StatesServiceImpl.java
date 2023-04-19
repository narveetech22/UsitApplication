package com.narvee.usit.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.States;
import com.narvee.usit.repository.IStatesRepository;
import com.narvee.usit.service.IStatesService;

@Service
public class StatesServiceImpl implements IStatesService {

	private static final Logger logger = LoggerFactory.getLogger(StatesServiceImpl.class);

	@Autowired
	private IStatesRepository repository;

	@Override
	public States saveStates(States states) {
		logger.info("!!! inside class : StatesServiceImpl, !! method: saveStates ");
		return repository.save(states);

	}

	@Override
	public List<States> getAllStates() {
		logger.info("!!! inside class : StatesServiceImpl, !! method: getAllStates ");
		return repository.findAll();
	}

	@Override
	public void deleteStates(long id) {
		logger.info("!!! inside class : StatesServiceImpl, !! method: deleteStates ");
		States states = repository.findById(id).get();
		repository.delete(states);
	}

	@Override
	public States getStatesById(long id) {
		logger.info("!!! inside class : StatesServiceImpl, !! method: getStatesById ");
		return repository.findById(id).get();
	}

	@Override
	public List<Object[]> getstates() {
		return repository.getstates();
	}

}
