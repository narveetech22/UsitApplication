package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.SalesSubmission;

public interface ISubmissionService {

	public SalesSubmission saveSubmission(SalesSubmission submission);

	public SalesSubmission getSubmissionByID(long sid);

	public boolean deleteSalesSubmission(long sid);

	public List<SalesSubmission> getAllSalesSubmission();

	Page<SalesSubmission> findPaginated(int pageNo, int pageSize);

	List<SalesSubmission> findByConsultantSidAndProjectlocationAndEndclient(long cid, String location,
			String endclient);

	public int toggleStatus(String status, String remarks, Long id);

}
