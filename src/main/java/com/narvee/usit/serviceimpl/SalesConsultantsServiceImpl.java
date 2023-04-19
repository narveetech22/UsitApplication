package com.narvee.usit.serviceimpl;

//import java.awt.print.Pageable;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.narvee.usit.entity.SalesConsultants;
import com.narvee.usit.entity.SalesSubmission;
import com.narvee.usit.repository.ISalesConsultantsRepository;
import com.narvee.usit.repository.ISalesSubmissionRepository;
import com.narvee.usit.service.ISalesConsultantsService;

@Service
@Transactional
public class SalesConsultantsServiceImpl implements ISalesConsultantsService {

	private static final Logger logger = LoggerFactory.getLogger(SalesConsultantsServiceImpl.class);

	@Autowired
	private ISalesConsultantsRepository iConsultantsRepo;

	@Autowired
	private ISalesSubmissionRepository submissionRepository;

	@Override
	public SalesConsultants saveConsultantss(SalesConsultants con) {
		logger.info("!!! inside class : SalesConsultantsServiceImpl, !! method : saveConsultantss");
		return iConsultantsRepo.save(con);
	}

	@Override
	public List<SalesConsultants> findAllConsultants() {
		logger.info("!!! inside class : SalesConsultantsServiceImpl, !! method : findAllConsultants");
		return iConsultantsRepo.findAll();
	}

	@Override
	public SalesConsultants getbyId(long id) {
		logger.info("!!! inside class : SalesConsultantsServiceImpl, !! method : getbyId");
		Optional<SalesConsultants> consultant = iConsultantsRepo.findById(id);
		if (consultant.isPresent()) {
			return consultant.get();
		}
		return null;
	}

	@Override
	public boolean deleteById(long id) {
		logger.info("!!! inside class : SalesConsultantsServiceImpl, !! method : deleteById");
		SalesSubmission submission = submissionRepository.findByConsultantSid(id);
		if (submission == null) {
			iConsultantsRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Page<SalesConsultants> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<SalesConsultants> findAll = iConsultantsRepo.findAll(pageable);
		return findAll;
	}

	@Override
	public int update(String resume, String h1b, String dl, long id) {
		Integer upd = iConsultantsRepo.update(resume, h1b, dl, id);
		if (upd == null) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public List<Object[]> getconsultInfo() {
		return iConsultantsRepo.getconsultInfo();
	}

	@Override
	public List<SalesConsultants> findByEmail(String email) {
		return iConsultantsRepo.findByEmail(email);
	}

	@Override
	public List<SalesConsultants> findByEmailAndSidNotEqual(String email, long id) {
		return iConsultantsRepo.findByEmailAndSidNot(email, id);
	}

	@Override
	public int toggleStatus(String status, String remarks, Long id) {
		int save = iConsultantsRepo.toggleStatus(status, remarks, id);
		if (save != 0)
			return 1;
		else
			return 0;
	}

}
