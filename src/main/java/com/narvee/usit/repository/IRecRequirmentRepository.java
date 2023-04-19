package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.narvee.usit.entity.Requirements;

public interface IRecRequirmentRepository extends JpaRepository<Requirements, Serializable> {

	/*
	 * @Query(value =
	 * "SELECT new com.narvee.usit.entity.RecRequirements(r.recruiterId,r.postedOn,r.jobTitle,r.location,r.vendor,r.employmenttype) FROM RecRequirements r"
	 * ) public List<RecRequirements> getAllRequirments();
	 * 
	 * @Query(value =
	 * "SELECT new com.narvee.usit.entity.RecRequirements(r.recruiterId,r.postedOn,r.jobTitle,r.location,r.vendor,r.employmenttype) FROM RecRequirements r"
	 * ) public Page<RecRequirements> getAllRequrimentsByPageNo(Pageable pageable);
	 * 
	 * @Query(value =
	 * "SELECT r.postedOn, r.vendor, r.jobTitle,r.location,r.employmenttype FROM RecRequirements r Where CONCAT(r.postedOn, r.vendor, r.jobTitle,r.location,r.employmenttype) LIKE %?1%"
	 * ) public List<Object[]> getAllRequirmentsByFilter(String keyword);
	 */

	// public Page<RecRequirements> getAllRequrimentsByPageNo(Pageable pageable);

	@Query(value = "select c.id, c.jobtitle, c.location, c.reqnumber from requirment c where c.status='Active' ", nativeQuery = true)
	public List<Object[]> getrequirements();

	@Query(value = "SELECT max(id) as maxno FROM requirment", nativeQuery = true)
	public String findmaxReqNumber();

}
