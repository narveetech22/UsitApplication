package com.narvee.usit.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.PinCode;
import com.narvee.usit.repository.IPincodeRepository;
import com.narvee.usit.service.IPincodeService;

@Service
public class PincodeServiceImpl implements IPincodeService {

	private static final Logger logger = LoggerFactory.getLogger(PincodeServiceImpl.class);

	@Autowired
	private IPincodeRepository repository;

	@Override
	public PinCode savePincode(PinCode code) {

		return repository.save(code);
	}

	@Override
	public List<PinCode> getAllPincode() {
		logger.info("!!! inside class: PincodeServiceImpl, !! method: getAllPincode");
		return repository.findAll();
	}

	@Override
	public void deletePincodeByID(long id) {
		logger.info("!!! inside class: PincodeServiceImpl, !! method: deletePincodeByID");
		PinCode code = repository.findById(id).get();
		repository.delete(code);
	}

	@Override
	public PinCode pincodeGetByID(long id) {
		logger.info("!!! inside class: PincodeServiceImpl, !! method: pincodeGetByID");
		return repository.findById(id).get();
	}

	@Override
	public List<Object[]> getpin() {
		// TODO Auto-generated method stub
		return repository.getpin();
	}

}
