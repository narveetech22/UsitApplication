package com.narvee.usit.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.ConsultantFileUploads;
import com.narvee.usit.entity.RecConsultant;
import com.narvee.usit.helper.ListRecruitingConsultant;
import org.springframework.core.io.Resource;

public interface IRecruitingConsultingService {

	public RecConsultant saveRecruitingConsutant(RecConsultant consultant);

	public List<ListRecruitingConsultant> getAllRecruitingConsultant();

	public RecConsultant getConsultantByID(long id);

	public boolean deleteRecruitingConsultantByID(long id);

//	public RecruitingConsultant getDetailsByConsultantName(String name, long id);

	public Page<List<RecConsultant>> findPaginated(int pageNo, int pageSize);

	public long changeStatus(String status, long id);

	public List<RecConsultant> getAllRecConsultantByFilter(String keyword);

	public int update(String resume, long id);

	public List<Object[]> getconsultants();

	public List<Object[]> getconsultantsbyRequirementId(long id);

	public List<RecConsultant> findByEmail(String email);

	public int toggleStatus(String status, Long id, String rem);

	public ConsultantFileUploads uploadfiles(String files, long id);

	public ConsultantFileUploads getFile(long id) throws FileNotFoundException;

	public Resource download(long id) throws FileNotFoundException;

}