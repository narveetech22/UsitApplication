package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.narvee.usit.entity.RecInterviews;
import com.narvee.usit.helper.IRecInterviewHelper;

@Repository
public interface IRecInterviewRepository extends JpaRepository<RecInterviews, Serializable> {

	public RecInterviews findBySubmissionSubmissionid(long id);

//	@Query( "SELECT rs.submissionId, rc.name, rr.jobtitle FROM ReqSubmission rs, RecConsultant rc, RecRequirements rr WHERE rs.consultant= rc.id AND rs.requirements = rr.recruiterId")
//	public List<Object[]> recSubmissiondetails();
//	
	@Query(value = "select i.id, c.name,i.interview_date,i.round,r.jobtitle, i.mode,s.createddate,u.fullname,i.timezone, i.interview_status   from recruiting_consultant c, reqsubmission s,  requirment r, recruiting_interiews i, users u\r\n"
			+ "where s.consultant_id = c.id and s.requirements_id = r.id and i.sub_id = s.sub_id and u.userid = i.addedby", nativeQuery = true)
	public List<IRecInterviewHelper> getAllRecInterviews();

	// @Query(value = "SELECT rc.name, ri.interviewdate, ri.round, ri.mode,
	// rr.job_title as jobtitle, rs.createddate, u.pseudoname, ri.interview_status
	// As interviewstatus, ri.status FROM recruiting_interiews ri,
	// recruiting_consultant rc, reqsubmission rs, users u, requirment rr WHERE
	// u.userid = ri.added_by AND ri.submission_id = rs.sub_id AND rs.consultant_id
	// = rc.id AND rr.id = rs.requirements_id AND CONCAT(rc.name, ri.interviewdate,
	// ri.round, ri.mode, rr.job_title as jobtitle, rs.createddate, u.pseudoname,
	// ri.interview_status As interviewstatus, ri.status) LIKE %?1%", nativeQuery =
	// true)
	// public List<IRecInterviewHelper> filterAllRecIntervies(String keyword);
	//

}
