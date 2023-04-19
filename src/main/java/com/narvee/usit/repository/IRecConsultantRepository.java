package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.narvee.usit.entity.RecConsultant;
import com.narvee.usit.helper.ListRecruitingConsultant;

public interface IRecConsultantRepository extends JpaRepository<RecConsultant, Serializable> {

	/*
	 * @Query(value =
	 * "SELECT new com.narvee.usit.entity.RecConsultant(rc.id,rc.createddate,rc.name,rc.experience,rc.technology,rc.email,v.status,u.firstname,rc.updatedby,rc.status) FROM RecConsultant rc, Visa v, Users u WHERE rc.visaid =v.vid AND rc.addedby = u.userid"
	 * ) public List<RecConsultant> getAllRequirments();
	 * 
	 * // @Query(value =
	 * "SELECT new com.narvee.Technology.entity.RecruitingConsultant(rc.id,rc.name,rc.mobile, rc.email,rc.visaid,rc.experience,rc.rate,rc.location,rc.technology,rc.skills,rc.summary,rc.resume) FROM   RecruitingConsultant rc WHERE rc.name=:name AND rc.id=:id"
	 * ) // public RecruitingConsultant getDetailsConsultantByName(@Param("name")
	 * String name, long id);
	 * 
	 * @Query(value =
	 * "SELECT new com.narvee.usit.entity.RecConsultant(rc.id,rc.createddate,rc.name,rc.experience,rc.technology,rc.email,v.status,u.firstname,rc.updatedby,rc.status) FROM RecConsultant rc, Visa v, Users u WHERE rc.visaid =v.vid AND rc.addedby = u.userid "
	 * ) public Page<List<RecConsultant>>
	 * getAllRequrimentsConsultantByPageNo(Pageable pageable);
	 * 
	 * 
	 * // @Query(value =
	 * "SELECT new com.narvee.usit.entity.RecConsultant(rc.id,rc.createddate,rc.name,rc.experience,rc.technology,rc.email,v.status,u.firstname,rc.updatedby,rc.status) FROM RecConsultant rc, Visa v, Users u WHERE CONCAT(rc.createddate,rc.name,rc.experience,rc.technology,rc.email,v.status,u.firstname,rc.updatedby,rc.status) LIKE %?1% "
	 * ) // public List<RecConsultant> getAllRecConsultantFilter(String keyword); //
	 * 
	 * @Query(value =
	 * "SELECT new com.narvee.usit.entity.RecConsultant(rc.id,rc.createddate,rc.name,rc.experience,rc.technology,rc.email,v.status,u.firstname,rc.updatedby,rc.status) FROM RecConsultant rc, Visa v, Users u WHERE rc.createddate LIKE CONCAT('%',:keyword, '%') OR rc.name LIKE CONCAT('%',:keyword, '%') OR rc.experience  LIKE CONCAT('%',:keyword, '%') OR rc.technology LIKE CONCAT('%',:keyword, '%') OR rc.email LIKE CONCAT('%',:keyword, '%') OR v.status LIKE CONCAT('%',:keyword, '%') OR u.firstname LIKE CONCAT('%',:keyword, '%') OR rc.updatedby LIKE CONCAT('%',:keyword, '%') OR rc.status LIKE CONCAT('%',:keyword, '%') AND rc.visaid =v.vid AND rc.addedby = u.userid"
	 * ) public List<RecConsultant> getAllRecConsultantFilter(String keyword);
	 * //rc.visaid =v.vid AND rc.addedby = u.userid AND
	 * 
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query("UPDATE RecConsultant c SET c.status = :status WHERE c.id = :id")
	 * public int toggleStatus(@Param("status") String status, @Param("id") long
	 * id);
	 */

//	select c.createddate, c.name, c.experience, c., v., u., c., c., c.id  from recruiting_consultant c, visa v, users u , technologies t
//	where v.id = c.visaid and u.userid = c.addedby and t.id = c. techid and c.status = 'Active' ListRecruitingConsultant
	@Query(value = "select u.userid,c.createddate, c.name, c.experience, c.email, v.visa_status, u.fullname, c.updateddate, c.status, c.id, t.technologyarea   from recruiting_consultant c, visa v, users u , technologies t where v.id = c.visaid and u.userid = c.addedby and t.id = c. techid ", nativeQuery = true)
	public List<ListRecruitingConsultant> getall();

	public List<RecConsultant> findByEmail(String email);

	@Modifying
	@Query("UPDATE RecConsultant c SET c.resume= :resume  WHERE c.id = :id")
	public int update(@Param("resume") String resume, @Param("id") Long id);

	@Query(value = "select c.id, c.name, t.technologyarea , c.experience  from recruiting_consultant c, technologies t  where t.id = c.techid and  c.status='Active' ", nativeQuery = true)
	public List<Object[]> getconsultants();

	@Query(value = "select c.id, c.name, t.technologyarea , c.experience  from recruiting_consultant c, technologies t  where t.id = c.techid and  c.status='Active' and c.requirements_id=:id", nativeQuery = true)
	public List<Object[]> getconsultantsByreqId(@Param("id") long id);

	@Modifying
	@Query("UPDATE RecConsultant c SET c.status = :status,c.remarks = :rem  WHERE c.id = :id")
	public int toggleStatus(@Param("status") String status, @Param("id") Long id, @Param("rem") String rem);

}
