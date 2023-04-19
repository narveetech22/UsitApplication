package com.narvee.usit.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Interview;
import com.narvee.usit.entity.SalesConsultants;
import com.narvee.usit.helper.ListInterview;
import com.narvee.usit.repository.ISalesConsultantsRepository;
import com.narvee.usit.repository.InterviewRepository;
import com.narvee.usit.service.IinterviewService;

@Service
public class InterviewServiceImpl implements IinterviewService {

	@Autowired
	private InterviewRepository repository;

	@Autowired
	private ISalesConsultantsRepository consRepo;;

	@Override
	public Interview saveIterview(Interview con) {
		return repository.save(con);

	}

	@Override
	public boolean deleteInterviewById(long id) {
		repository.deleteById(id);
		return true;
	}

//List<ListInterview> getall();
	@Override
	public List<ListInterview> getAllDetailsInterview() {
		return repository.getall();
	}

	@Override
	public Interview getInterviewByID(long id) {
		Optional<Interview> interview = repository.findById(id);
		if (interview.isPresent()) {
			return interview.get();
		}
		return null;
	}

	@Override
	public Page<Interview> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<Interview> findAll = repository.findAll(pageable);
		return findAll;
	}

	@Override
	public SalesConsultants getSalesConsById(long conid) {
		return consRepo.findById(conid).get();
	}

	@Override
	public List<Object[]> submissiondetails() {
		return repository.submissiondetails();
	}

//	@Override
//	public Page<ListInterview> findPaginateded(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//		Page<ListInterview> findAll = repository.getallbypage(pageable);
//		return findAll;
//	}

	@Override
	public Page<List<ListInterview>> findPaginateded(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<List<ListInterview>> findAll = repository.getallbypage(pageable);
		return findAll;
	}

}
