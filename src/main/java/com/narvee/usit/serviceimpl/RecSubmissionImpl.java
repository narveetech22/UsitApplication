package com.narvee.usit.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.RecInterviews;
import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.helper.ListRecSubmissions;
import com.narvee.usit.repository.IRecInterviewRepository;
import com.narvee.usit.repository.IRecSubmissionRepository;
import com.narvee.usit.repository.InterviewRepository;
import com.narvee.usit.service.IRecSubmissionService;

@Service
public class RecSubmissionImpl implements IRecSubmissionService {

	private static final Logger logger = LoggerFactory.getLogger(RecSubmissionImpl.class);

	@Autowired
	private IRecSubmissionRepository repo;

	@Autowired
	private IRecInterviewRepository interviewrepo;

	@Autowired
	private EmailService emailService;

	@Override
	public ReqSubmission saveSubmission(ReqSubmission submission) {
		logger.info("!!! inside class :RecSubmissionImpl, !! method : saveSubmission");
		try {
			emailService.sendEmail(submission);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		return repo.save(submission);
	}

	@Override
	public List<ReqSubmission> getAllsubmission() {
		logger.info("!!! inside class :RecSubmissionImpl, !! method : getAllsubmission");
		return repo.findAll();
	}

	@Override
	public Optional<ReqSubmission> getSubmissionByID(long id) {
		logger.info("!!! inside class :RecSubmissionImpl, !! method : getSubmissionByID");
		return repo.findById(id);
	}

	@Override
	public boolean deleteSubmissionByID(long id) {
		logger.info("!!! inside class :RecSubmissionImpl, !! method : deleteSubmissionByID");
		RecInterviews interviews = interviewrepo.findBySubmissionSubmissionid(id);
		if (interviews == null) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Page<List<ReqSubmission>> findPaginated(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//		Page<List<ReqSubmission>> findAll = repo.AllsubmissionsByPageNo(pageable);
		return null; // findAll;
	}

	@Override
	public List<ListRecSubmissions> getall() {
		return repo.getall();
	}

	@Override
	public List<ReqSubmission> findByConsultantIdAndRequirementsRequirementidAndEndclient(long cid, long rid,
			String client) {
		return repo.findByConsultantIdAndRequirementsRequirementidAndEndclient(cid, rid, client);
	}

	@Override
	public List<Object[]> getsubdetails() {
		return repo.getsubdetails();
	}

	@Override
	public ReqSubmission updateSubmission(ReqSubmission submission) {
		try {
			emailService.sendEmail(submission);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		return repo.save(submission);
	}

}
