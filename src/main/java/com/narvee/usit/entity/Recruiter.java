package com.narvee.usit.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "Recruiter")
public class Recruiter {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recid;

	private String recruiter;

	private String usnumber;

	private String innumber;

	private String country;

	private String state;

	private String iplogin;

	private String fedid;

	private String email;

	private String linkedin;

	@Column(name = "status")
	private String status = "Active";

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "createddate", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate createddate;

	@Column(name = "updatedby")
	private long updatedby;

	@UpdateTimestamp
	private LocalDate updateddate;

	private long role;

	@Column(length = 3000)
	private String remarks;

	@Transient
	public String addedbyname;

	private String rec_stat = "Entry";

	private String designation;
//	
	@OneToOne
	private Users user;

	private String details;

	// @OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	@OneToOne
	private VendorDetails vendor;

	// @OneToOne(cascade = CascadeType.ALL)
	@OneToOne
	private States states;

	public Recruiter() {

	}

}
