package com.narvee.usit.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import com.narvee.usit.entity.Vms;

public interface IVmsService {
	public void save(MultipartFile file);

	public boolean saveVms(Vms portalVms);

	public List<Vms> findAllVms();

	public List<Vms> findVmsbyid(long id);

	public List<Vms> dupvendor(String email);

	public Vms getbyId(long id);

	public boolean deleteById(long id);

	public int changeStatus(String status, Long id, String remarks);

	public List<Vms> filterVms(String keyword);

	public Page<Vms> findPaginated(int pageNo, int pageSize);

	public int approve(Vms vms);

}