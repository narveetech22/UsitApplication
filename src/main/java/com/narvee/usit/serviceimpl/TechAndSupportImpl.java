package com.narvee.usit.serviceimpl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.narvee.usit.entity.Roles;
import com.narvee.usit.entity.TechAndSupport;
import com.narvee.usit.repository.ITechSupportRepository;
import com.narvee.usit.service.ITechSupportService;

@Transactional
@Service
public class TechAndSupportImpl implements ITechSupportService {
	public static final Logger logger = LoggerFactory.getLogger(TechAndSupportImpl.class);

	@Autowired
	private ITechSupportRepository repository;

	@Override
	public TechAndSupport saveTechSupp(TechAndSupport entity) {
		logger.info("TechAndSupportImpl.saveTechSupp()");
		return repository.save(entity);
	}

	@Override
	public List<TechAndSupport> getAll(String search) {
		logger.info("TechAndSupportImpl.getAll()");
		List<TechAndSupport> findAlln = new ArrayList();
		System.out.println(search);
		if (!search.equals("dummysearch")) {
			findAlln = repository.getAll(search.trim());
		} else if (search.equals("dummysearch")) {
			findAlln = repository.findAll();
		} else {
			findAlln = repository.findAll();
		}
		return findAlln;
	}

	@Override
	public TechAndSupport getTechSupp(Long id) {
		logger.info("TechAndSupportImpl.getTechSuppByhid()");
		return repository.findById(id).get();
	}

	@Override
	public boolean deleteSupp(Long id) {
		logger.info("TechAndSupportImpl.deleteSupp()");
		repository.deleteById(id);
		return true;
	}

	@Override
	public int changeStatus(String status, Long id, String rem) {
		logger.info("TechAndSupportImpl.changeStatus()");
		return repository.toggleStatus(status, id, rem);
	}

	@Override
	public List<TechAndSupport> all() {
		return repository.findAll();
	}

}
