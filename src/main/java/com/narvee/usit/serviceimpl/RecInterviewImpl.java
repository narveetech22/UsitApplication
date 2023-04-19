package com.narvee.usit.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.RecInterviews;
import com.narvee.usit.helper.IRecInterviewHelper;
import com.narvee.usit.repository.IRecInterviewRepository;
import com.narvee.usit.service.IRecInterviewService;

@Service
public class RecInterviewImpl implements IRecInterviewService {

	@Autowired
	private IRecInterviewRepository repository;

	@Autowired
	private EmailService emailService;

	@Override
	public RecInterviews saveInterviews(RecInterviews interviews) {
		System.out.println(interviews);

		try {
			emailService.sendEmail2(interviews);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}

		return repository.saveAndFlush(interviews);

	}

	@Override
	public List<RecInterviews> getAllRecInterviewDetails() {
		return repository.findAll();
	}

	@Override
	public RecInterviews getRecInterviewById(long id) {
		Optional<RecInterviews> interviews = repository.findById(id);
		if (interviews.isPresent()) {
			return interviews.get();
		}
		return null;
	}

	@Override
	public boolean deleteRecInterviewByID(long id) {
		RecInterviews interviews = repository.findById(id).get();
		if (interviews != null) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Page<RecInterviews> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<RecInterviews> findAll = repository.findAll(pageable);
		return findAll;

	}

	@Override
	public List<Object[]> getRecSubmissionDetails() {
		return null;// repository.recSubmissiondetails();
	}

	@Override
	public List<IRecInterviewHelper> getAllRecInterviews() {
		return repository.getAllRecInterviews();// repository.getAllRecInterviews();
	}

	@Override
	public List<IRecInterviewHelper> filterRecInterviews(String keyword) {
		if (keyword != null) {
			return null;// repository.filterAllRecIntervies(keyword);
		}
		return null;// repository.getAllRecInterviews();
	}

}
