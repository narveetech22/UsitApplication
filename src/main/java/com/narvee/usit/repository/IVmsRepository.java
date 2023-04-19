package com.narvee.usit.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.Vms;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVmsRepository extends JpaRepository<Vms, Serializable> {

}
