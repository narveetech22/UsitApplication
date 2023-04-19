package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.narvee.usit.entity.Visa;

public interface IVisaRepository extends JpaRepository<Visa, Serializable> {
	@Query(value = "select v.id, v.visa_status from visa v ", nativeQuery = true)
	public List<Object[]> getvisaidname();
}
