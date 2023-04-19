package com.narvee.usit.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.entity.GetVendor;
import com.narvee.usit.entity.VendorDetails;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.helper.ListVendor;

public interface IVendorService {
	public VendorDetails save(VendorDetails vms);

	public Vms save(Vms vms);

	public List<VendorDetails> getall();

	public boolean deleteById(long id);

	public VendorDetails getbyId(long id);

	public List<GetVendor> getvendordetails();

	public int approve(String stat, Long id, Long role);

	public int changeStatus(String status, Long id, String remarks);

	public Page<VendorDetails> findPaginated(int pageNo, int pageSize);

	public void saveexcel(MultipartFile file);

	public List<ListVendor> getvendor();

	public List<VendorDetails> findByCompanyAndCityIdAndStatesId(String company, long cityid, long stateid);
}
