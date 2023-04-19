package com.narvee.usit.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.SalesSubmission;

public interface ISalesSubmissionRepository extends JpaRepository<SalesSubmission, Serializable> {

	
	public List<SalesSubmission> findByConsultantSidAndProjectlocationAndEndclient(long cid, String location,
			String endclient);

	@Query(value = "SELECT ss.subid, sc.id As sid, sc.full_name AS fullName, ss.created_date,ss.position_title AS positionTitle, ss.end_client AS endClient,ss.vendor,ss.subrate,u.userid,u.firstname FROM sales_submission ss, users u, tbl_sales_consultant sc WHERE u.userid = ss.addedby AND ss.consultant = sc.id", nativeQuery = true)
	// public List<ISalesSubmissionHelper> getAllSubmission();
	public List<Object[]> getAllSubmission();

//	@Query(value = "SELECT ss.subid, sc.id As sid, sc.full_name AS fullName, ss.created_date,ss.position_title AS positionTitle, ss.end_client AS endClient,ss.vendor,ss.subrate,u.userid,u.firstname FROM sales_submission ss, users u, tbl_sales_consultant sc WHERE u.userid = ss.addedby AND ss.consultant = sc.id", countQuery = "SELECT count(*) FROM sales_submission ss, users u, tbl_sales_consultant sc", nativeQuery = true)
//	public Page<List<ISalesSubmissionHelper>> getSalesSubmissionByPageNo(Pageable pageable);

	@Modifying
	@Query("UPDATE SalesSubmission c SET c.status = :status,c.remarks = :rem  WHERE c.subid = :id")
	public int toggleStatus(@Param("status") String status, @Param("rem") String rem, @Param("id") Long id);
	
	// foreign key relation
	public SalesSubmission findByConsultantSid(long id);

}
