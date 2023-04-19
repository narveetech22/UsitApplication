package com.narvee.usit.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Interview;
import com.narvee.usit.entity.SalesSubmission;
import com.narvee.usit.repository.ISalesSubmissionRepository;
import com.narvee.usit.repository.InterviewRepository;
import com.narvee.usit.service.ISubmissionService;

@Service
public class SalesSubmissionImpl implements ISubmissionService {

	private static final Logger logger = LoggerFactory.getLogger(SalesSubmissionImpl.class);

	@Autowired
	private ISalesSubmissionRepository repo;

	@Autowired
	private InterviewRepository interviewRepository;

	@Override
	public SalesSubmission saveSubmission(SalesSubmission submission) {
		logger.info("!!! inside class : SalesSubmissionImpl, !! method : saveSubmission");
		return repo.save(submission);
	}

	@Override
	public SalesSubmission getSubmissionByID(long sid) {
		logger.info("!!! inside class : SalesSubmissionImpl, method : getSubmissionByID");
		Optional<SalesSubmission> submission = repo.findById(sid);
		if (submission.isPresent()) {
			return submission.get();
		}
		return null;
	}

	@Override
	public boolean deleteSalesSubmission(long sid) {
		logger.info("!!! inside class : SalesSubmissionImpl, method : deleteSalesSubmission");
		Interview interview = interviewRepository.findBySubmissionSubid(sid);
		if (interview == null) {
			repo.deleteById(sid);
			return true;
		}
		return false;
	}

	@Override
	public List<SalesSubmission> getAllSalesSubmission() {
		logger.info("!!! inside class : SalesSubmissionImpl, method : getAllSalesSubmission");
		return repo.findAll();
	}

	@Override
	public Page<SalesSubmission> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<SalesSubmission> findAll = repo.findAll(pageable);
		return findAll;
	}

	@Override
	public List<SalesSubmission> findByConsultantSidAndProjectlocationAndEndclient(long cid, String location,
			String endclient) {
		return repo.findByConsultantSidAndProjectlocationAndEndclient(cid, location, endclient);
	}

//	@Override
//	public List<Object[]> getConDetailsBySubmission() {
//		return repo.getconsultantDetailsBySub();
//	}

	@Override
	public int toggleStatus(String status, String remarks, Long id) {
		int save = repo.toggleStatus(status, remarks, id);
		if (save != 0)
			return 1;
		else
			return 0;
	}

}
