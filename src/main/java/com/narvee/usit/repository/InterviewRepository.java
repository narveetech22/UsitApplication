package com.narvee.usit.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.narvee.usit.entity.Interview;
import com.narvee.usit.entity.RecInterviews;
import com.narvee.usit.helper.ListInterview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Serializable> {

	@Query(value = "select s.id, c.name, s.vendor, s.position, s.project_location, s.end_client from sales_submission s, tbl_sales_consultant c where s.consultant_id = c.id ", nativeQuery = true)
	public List<Object[]> submissiondetails();

	@Query(value = "select c.id as consid, c.name, i.interview_date, i.round, i.mode, s.vendor, s.created_date, u.userid, u.fullname, i.interview_status, i.time_zone, i.id as intrid, s.id as subid\r\n"
			+ "from interview i, tbl_sales_consultant c, sales_submission s , users u where i.submission_id = s.id and s.consultant_id = c.id and i.addedby = u.userid", nativeQuery = true)
	public List<ListInterview> getall();

	@Query(value = "select c.id as consid, c.name, i.interview_date, i.round, i.mode, s.vendor, s.created_date, u.userid, u.fullname, i.interview_status, i.time_zone, i.id as intrid, s.id as subid\r\n"
			+ "from interview i, tbl_sales_consultant c, sales_submission s , users u where i.submission_id = s.id and s.consultant_id = c.id and i.addedby = u.userid", countQuery = "SELECT count(*) FROM interview ", nativeQuery = true)
	public Page<List<ListInterview>> getallbypage(Pageable pageable);

	// foreign key relation
	public Interview findBySubmissionSubid(long id);

}
