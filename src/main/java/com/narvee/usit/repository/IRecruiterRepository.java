package com.narvee.usit.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.Recruiter;
import com.narvee.usit.entity.VendorDetails;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.helper.ListRecruiter;

public interface IRecruiterRepository extends JpaRepository<Recruiter, Long> {
	public List<Recruiter> findByEmail(String name);

	public List<Recruiter> findByVendorVmsid(long id);

	@Modifying
	@Query("UPDATE Recruiter c SET c.status = :status,c.remarks = :rem  WHERE c.recid = :id")
	public int toggleStatus(@Param("status") String status, @Param("id") Long id, @Param("rem") String rem);

	// public Recruiter(Long recid, String recruiter, String usnumber, String
	// innumber, String country, String state, String iplogin, String fed_id, long
	// vmsid, String status, LocalDate createddate, long addedby, long role, String
	// addedbyname) {

//	@Query("SELECT new com.narvee.usit.entity.Recruiter(v.recid, v.recruiter, v.usnumber, v.innumber, v.country, v.state, v.iplogin, v.fed_id, v.status, v.createddate, v.addedby, v.role,  u.fullname,v.rec_stat) from "
//			+ "Recruiter v , Users u  where v.addedby = u.userid AND v.rec_stat!='Rejected'")
	// public List<Recruiter> getall();

	@Modifying
	@Query("UPDATE Recruiter c SET c.rec_stat = :status,c.role = :role  WHERE c.recid = :id")
	public int approveStatus(@Param("status") String status, @Param("id") Long id, @Param("role") long role);

	@Query(value = "select r.id,r.recruiter, r.usnumber,u.fullname, r.email, r.country, s.statename, r.fedid, r.createddate, r.status, r.rec_stat, v.company from recruiter r , states s,  vendor v, users u where s.id= r.states_id and u.userid = r.user_userid and v.id = r.vendor_id and r.rec_stat!='Rejected'", nativeQuery = true)
	public List<ListRecruiter> getallrecruiter();

}
