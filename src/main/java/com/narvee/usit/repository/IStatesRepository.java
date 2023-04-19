package com.narvee.usit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.narvee.usit.entity.States;

@Repository
public interface IStatesRepository extends JpaRepository<States, Long> {

	public States findByStatename(String state);

	@Query(value = "select s.id, s.statename from states s where s.status='Active'", nativeQuery = true)
	public List<Object[]> getstates();
}
