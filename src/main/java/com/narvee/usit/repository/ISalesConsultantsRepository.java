package com.narvee.usit.repository;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.narvee.usit.entity.SalesConsultants;

@Repository
public interface ISalesConsultantsRepository extends JpaRepository<SalesConsultants, Long> {

	public List<SalesConsultants> findByEmail(String email);

	public List<SalesConsultants> findByEmailAndSidNot(String email, long id);

	@Modifying
	@Query("UPDATE SalesConsultants c SET c.resumepath = :resume,c.h1bcopy = :h1bcopy , c.dlcopy=:dlcopy  WHERE c.sid = :id")
	public int update(@Param("resume") String resume, @Param("h1bcopy") String h1bcopy, @Param("dlcopy") String dlcopy,
			@Param("id") Long id);

	@Query(value = "select s.id,s.name, t.technologyarea,s.experience from tbl_sales_consultant s, technologies t where t.id = s.technology and s.status='Active' order by s.name", nativeQuery = true)
	public List<Object[]> getconsultInfo();

	@Modifying
	@Query("UPDATE SalesConsultants c SET c.status = :status,c.remarks = :rem  WHERE c.sid = :id")
	public int toggleStatus(@Param("status") String status, @Param("rem") String rem, @Param("id") Long id);

}