package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.helper.ListRecSubmissions;
import com.narvee.usit.helper.ListRecruitingConsultant;

public interface IRecSubmissionRepository extends JpaRepository<ReqSubmission, Serializable> {

	public ReqSubmission findByRequirementsRequirementid(long breqID);

	public ReqSubmission findByConsultantId(long id);

//	@Query(value = "SELECT new com.narvee.usit.helper.RecSubmissionHelper(rc.id,s.submissionId,s.createddate,rc.name,s.rate,"
//			+ "s.recruitername,s.status) FROM RecConsultant rc, "
//			+ " Reqsubmission s WHERE rc.id =s.consultantId")
//	public List<RecSubmissionHelper> getsubmissions();
//	
	/*
	 * @Query(value =
	 * "SELECT new com.narvee.usit.entity.ReqSubmission(rs.submissionId,rs.consultantId,rs.requirementId, rs.createddate, rc.name, rs.rate,u.fullname,rs.substatus,rs.status, u.userid) FROM ReqSubmission rs,RecConsultant rc, Users u WHERE rs.consultantId= rc.id AND rs.addedby = u.userid"
	 * ) public Page<List<ReqSubmission>> AllsubmissionsByPageNo(Pageable pageable);
	 * 
	 * @Query(value =
	 * "SELECT new com.narvee.usit.entity.ReqSubmission(rs.submissionId,rs.consultantId,rs.requirementId, rs.createddate, rc.name, rs.rate,u.fullname,rs.substatus,rs.status, u.userid) FROM ReqSubmission rs,RecConsultant rc, Users u WHERE rs.consultantId= rc.id AND rs.addedby = u.userid"
	 * ) public List<ReqSubmission> getAllSubmission();
	 * 
	 * // @Query(value =
	 * "SELECT new com.narvee.usit.entity.ReqSubmission(rs.submissionId,rs.consultantId,rs.requirementId, rs.createddate, rc.name, rs.rate,u.fullname,rs.substatus,rs.status, u.userid) FROM ReqSubmission rs,RecConsultant rc, Users u WHERE rs.consultantId= rc.id AND rs.addedby = u.userid AND  rs.createddate LIKE CONCAT('%',:keyword, '%') OR rc.name LIKE CONCAT('%',:keyword, '%') OR rs.rate LIKE CONCAT('%',:keyword, '%') OR u.fullname LIKE CONCAT('%',:keyword,'%') OR rs.substatus LIKE CONCAT('%',:keyword, '%') OR rs.status LIKE CONCAT('%',:keyword,'%')"
	 * ) // public List<ReqSubmission> getAllSubmissionByFilter(String keyword);
	 * 
	 * @Query(value =
	 * "SELECT r.recruiter_id, r.job_title, r.location FROM requirment r",
	 * nativeQuery = true) public List<Object[]> getAllRequirments();
	 * 
	 * 
	 * @Query(value =
	 * "SELECT rc.id, rc.experience, rc.name, rc.technology FROM recruiting_consultant rc "
	 * , nativeQuery = true) public List<Object[]> getAllRecConsultants();
	 * 
	 * @Query(value = "SELECT u.userid, u.fullname FROM users u", nativeQuery =
	 * true) public List<Object[]> getAllUsers();
	 */

	// select u.userid,c.id, r.sub_id, c.name,u.fullname, r.createddate,
	// r.endclient,r.consultant_id, r.rate, r.submittedby, r.substatus, r.status,
	// r.sub_id
	// from reqsubmission r, users u, recruiting_consultant c where c.id =
	// r.consultant_id and u.userid = r. submittedby

	@Query(value = "select u.userid,c.id, r.sub_id,rq.jobtitle, c.name,u.fullname, r.createddate, r.endclient,r.consultant_id, r.rate, r.submittedby, r.substatus, r.status   from reqsubmission r, users u, recruiting_consultant c, requirment rq where c.id = r.consultant_id and u.userid = r. submittedby and rq.id = r.requirements_id and r.status = 'Active'", nativeQuery = true)
	public List<ListRecSubmissions> getall();

	@Query(value = "select s.sub_id, c.name,  r.jobtitle,  s.endclient, r.location from recruiting_consultant c, requirment r, reqsubmission s\r\n"
			+ "where    s.requirements_id = r.id  and s.consultant_id = c.id and s.status='Active' order by c.name asc", nativeQuery = true)
	public List<Object[]> getsubdetails();

	public List<ReqSubmission> findByConsultantIdAndRequirementsRequirementidAndEndclient(long cid, long rid,
			String client);
}
