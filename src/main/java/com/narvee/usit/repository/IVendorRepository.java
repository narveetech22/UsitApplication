package com.narvee.usit.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.GetVendor;
import com.narvee.usit.entity.VendorDetails;
import com.narvee.usit.helper.ListVendor;

public interface IVendorRepository extends JpaRepository<VendorDetails, Long> {

	public VendorDetails findByCompany(String company);

	public List<VendorDetails> findByCompanyAndCityIdAndStatesId(String company, long cityid, long stateid);

	public List<VendorDetails> findByCompanyAndCityCitynameAndStatesStatenameAndFedid(String company, String city,
			String state, String fed_id);

	public List<VendorDetails> findByCompanyAndCityIdAndStatesIdAndFedid(String company, long cityid, long stateid,
			String fedid);

	@Modifying
	@Query("UPDATE VendorDetails c SET c.status = :status,c.remarks = :rem  WHERE c.vmsid = :id")
	public int toggleStatus(@Param("status") String status, @Param("id") Long id, @Param("rem") String rem);

	@Query(value = "SELECT id, company from vendor where status!='InActive' AND vms_stat='Approved'", nativeQuery = true)
	public List<GetVendor> listvendor();

	@Modifying
	@Query("UPDATE VendorDetails c SET c.vms_stat = :status,c.role = :role  WHERE c.vmsid = :id")
	public int approveStatus(@Param("status") String status, @Param("id") Long id, @Param("role") long role);

	@Query(value = "select v.id,v.company, v.location1, v.location2, c.cityname, s.statename, p.pincode ,u.fullname, v.fedid, v.vendortype, v.tyretype,v.createddate,v.vms_stat, v.status  from vendor v, city c , states s , pin_code p , users u where c.id = v.city_id and s.id = states_id and p.id = v.pincode_id and u.userid = v. user_userid and v.vms_stat!='Rejected'", nativeQuery = true)
	public List<ListVendor> getvendor();

//	@Query("SELECT new com.narvee.usit.entity.VendorDetails(v.vmsid, v.company, v.location1, v.location2, v.city, v.state, v.zipcode, v.fed_id, v.tyretype, v.vendortype, v.client, v.vms_stat, v.status, v.createddate, v.role, u.fullname) from "
//			+ "VendorDetails v , Users u  where v.addedby = u.userid AND v.vms_stat!='Rejected'")
	// public List<VendorDetails> getall();

//	@Query("SELECT c FROM Vms c  WHERE c.addedby = :id")
//	public List<Vms> searchbyid(@Param("id") long id);

	// VendorDetails(Long vmsid, String company, String location1, String location2,
	// String city, String state, String zipcode, String fed_id, String tyretype,
	// String vendortype, String client, String vms_stat, String status, LocalDate
	// createddate,
	// long addedby, long role, String addedbyname)
	//

//	@Query("SELECT new com.narvee.usit.entity."
//			+ "Vms(v.id,v.companyname,v.recruitername,v.mobile,v.email,v.headQuarters,v.vendortype,u.firstname,v.createddate,v.status,v.tyretype) from Vms v,Users u where v.addedby=u.userid and v.vms_stat not in (:Approve) ")
//	public List<Vms> getallvms(@Param("Approve") String status);

//	@Query(value = "select v.id, v.company_name, v.recruiter_name, v.cp_mobile, v.cp_email, v.headquaters, v.category, u.pseudoname, v.updated_date, v.created_date from vms v, users u where v.added_by=u.userid and v.vms_stat not in ('reject')", nativeQuery = true)
//	public List<VmsProj> getallvms();

//	@Query("SELECT new com.narvee.usit.entity."
//			+ "Vms(v.id,v.companyname,v.recruitername,v.mobile,v.email,v.headQuarters,v.vendortype,u.firstname,v.createddate,v.status,v.tyretype) from Vms v,Users u where v.addedby=u.userid and v.createddate=:date")
//	public List<Vms> getallvmsbydate(LocalDate date);
//	@Query(value = "select v.id, v.company_name, v.recruiter_name, v.cp_mobile, v.cp_email, v.headquarters, v.category, u.pseudoname, v.created_date from vms v, users u where v.added_by=u.userid and v.vms_stat not in ('reject') ", nativeQuery = true)
//	public List<VmsProj> getallvmsbydate(LocalDate date);
//
//	@Query("SELECT new com.narvee.usit.entity."
//			+ "Vms(v.id,v.companyname,v.recruitername,v.mobile,v.email,v.headQuarters,v.vendortype,u.firstname,v.createddate,v.status,v.tyretype) from Vms v,Users u where v.addedby=u.userid")
//	Page<Vms> getallvms(Pageable pageable);

//	@Query("SELECT new com.narvee.usit.entity."
//			+ "Vms(v.id,v.companyname,v.recruitername,v.mobile,v.email,v.headQuarters,v.vendortype,u.firstname,v.createddate,v.status) from Vms v,Users u where v.addedby=u.userid and CONCAT(v.company_name,  v.recruiter_name,v.cp_mobile, v.cpemail,v.headQuarters, v.vendortype, v.status,  v.addedby,  v.createddate,u.firstname) LIKE %?1%")
//	public List<Vms> getVMSFilter(String keyword);

}
