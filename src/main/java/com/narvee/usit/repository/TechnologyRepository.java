package com.narvee.usit.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.domain.Pageable;
import com.narvee.usit.entity.Technologies;
import org.springframework.data.domain.Pageable;

public interface TechnologyRepository extends JpaRepository<Technologies, Long> {

	@Query(value = "select s.id, s.technologyarea from technologies s where s.status='Active' ", nativeQuery = true)
	List<Object[]> gettechnologies();

	Page<Technologies> findByTechnologyareaContaining(String name, Pageable pageable);

	@Query("select new com.narvee.usit.entity.Technologies(t.id,t.technologyarea,t.listofkeyword,t.comments,t.status,t.createddate,t.remarks)   from Technologies t")
	Page<List<Technologies>> getalltech(Pageable pageable);

	@Modifying
	@Query("UPDATE Technologies c SET c.status = :status,c.remarks = :rem  WHERE c.id = :id")
	public int toggleStatus(@Param("status") String status, @Param("id") long id, @Param("rem") String rem);
}
