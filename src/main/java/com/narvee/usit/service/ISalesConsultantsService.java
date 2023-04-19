package com.narvee.usit.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.SalesConsultants;

public interface ISalesConsultantsService {
	public SalesConsultants saveConsultantss(SalesConsultants salesConsultants);

	public List<SalesConsultants> findAllConsultants();

	public SalesConsultants getbyId(long id);

	public boolean deleteById(long id);

	public Page<SalesConsultants> findPaginated(int pageNo, int pageSize);

	public int update(String resume, String h1b, String dl, long id);

	public List<Object[]> getconsultInfo();

	public List<SalesConsultants> findByEmail(String email);

	public List<SalesConsultants> findByEmailAndSidNotEqual(String email, long id);

	public int toggleStatus(String status, String remarks, Long id);

}
