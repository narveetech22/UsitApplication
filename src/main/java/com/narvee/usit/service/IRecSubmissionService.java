package com.narvee.usit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.helper.ListRecSubmissions;

public interface IRecSubmissionService {

	public ReqSubmission saveSubmission(ReqSubmission submission);

	public ReqSubmission updateSubmission(ReqSubmission submission);

	public List<ReqSubmission> getAllsubmission();

	public Optional<ReqSubmission> getSubmissionByID(long id);

	public boolean deleteSubmissionByID(long id);

	public Page<List<ReqSubmission>> findPaginated(int pageNo, int pageSize);

	public List<ListRecSubmissions> getall();

	public List<ReqSubmission> findByConsultantIdAndRequirementsRequirementidAndEndclient(long cid, long rid,
			String client);

	public List<Object[]> getsubdetails();

}
