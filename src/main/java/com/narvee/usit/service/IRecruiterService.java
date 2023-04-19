package com.narvee.usit.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.entity.ExceRec;
import com.narvee.usit.entity.Recruiter;
import com.narvee.usit.entity.VendorDetails;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.helper.ListRecruiter;

public interface IRecruiterService {
	public Recruiter save(Recruiter vms);

	public List<Recruiter> getall();

	public ExceRec save(ExceRec vms);

	public List<ExceRec> duprecru(String email);

	public boolean deleteById(long id);

	public Recruiter getbyId(long id);

	public List<Recruiter> recruiterinfobyVmsid(long id);

	public List<Recruiter> dupvendor(String email);

	public int changeStatus(String status, Long id, String remarks);

	public int approve(String stat, Long id, Long role);

	public void saveexcel(MultipartFile file);

	public List<ListRecruiter> getallrecruiter();
}
