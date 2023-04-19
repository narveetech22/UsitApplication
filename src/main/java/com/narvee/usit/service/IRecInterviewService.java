package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.RecConsultant;
import com.narvee.usit.entity.RecInterviews;
import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.helper.IRecInterviewHelper;

public interface IRecInterviewService {

	public RecInterviews saveInterviews(RecInterviews interviews);

	public List<RecInterviews> getAllRecInterviewDetails();

	public RecInterviews getRecInterviewById(long id);

	public boolean deleteRecInterviewByID(long id);

	public List<Object[]> getRecSubmissionDetails();

	public List<IRecInterviewHelper> getAllRecInterviews();

	public Page<RecInterviews> findPaginated(int pageNo, int pageSize);

	public List<IRecInterviewHelper> filterRecInterviews(String keyword);

}
