package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.narvee.usit.entity.TechAndSupport;

public interface ITechSupportRepository extends JpaRepository<TechAndSupport, Serializable> {

	// private TechAndSupport(Long id, String name, int experience, String skills,
	// String email, long mobile, int status) {

	// Long id, String name, int experience, String skills, String email, long
	// mobile, String status
	// @Query("SELECT new com.narvee.usit.entity.TechAndSupport(t.id, t.name,
	// t.experience, t.skills, t.email, t.mobile, t.status ) from TechAndSupport t")

	@Query("SELECT new com.narvee.usit.entity.TechAndSupport(t.id, t.name,t.technology, t.experience, t.skills, t.email, t.mobile, t.status ) from  TechAndSupport t where CONCAT(t.name, t.experience, t.skills, t.email, t.mobile, t.status, t.technology) like %?1%")
	public List<TechAndSupport> getAll(String search);

	@Modifying
	@Query("UPDATE TechAndSupport c SET c.status = :status,c.remarks = :rem  WHERE c.id = :id")
	public int toggleStatus(@Param("status") String status, @Param("id") Long id, @Param("rem") String rem);

}
