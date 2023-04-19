package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "privileges")
public class Previleges {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long previd;

	private String previlegename;

	private String url;

	private Long roleid;

	private String status = "Active";

	@Column(name = "updateddate")
	@UpdateTimestamp
	private LocalDateTime updateddate;

	@Column(name = "addedby", nullable = false, updatable = false)
	private long addedby = 1;

	@Column(name = "updatedby")
	private long updatedby;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createddate", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate createddate;

}
