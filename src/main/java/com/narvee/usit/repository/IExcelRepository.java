package com.narvee.usit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.narvee.usit.entity.ExceRec;

public interface IExcelRepository extends JpaRepository<ExceRec, Long> {

	public List<ExceRec> findByEmail(String email);
}
