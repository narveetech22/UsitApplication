package com.narvee.usit.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "vendor")
public class VendorDetails {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vmsid;

	private String company;

	private String location1;

	private String location2;

	// private String zipcode;

	private String fedid;

	private String Country;

	private String tyretype;

	private String vendortype;

	private String client;

	private String vms_stat = "Entry";

	@Column(name = "status")
	private String status = "Active";

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "createddate", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate createddate;

	@Transient
	private long addedby;

	@Column(name = "updatedby")
	private long updatedby;

	@UpdateTimestamp
	private LocalDate updateddate;

	private long role;

	@Transient
	private String addedbyname;

	@Column(length = 3000)
	private String remarks;

	private String staff;

	private String employeecount;

	private String revenue;

	private String website;

	private String facebook;

	private String phonenumber;

	private String industrytype;
	private String details;

	private String linkedinid;

	private String twitterid;

	@OneToOne
	// @OneToOne(cascade = CascadeType.ALL)
	// @OneToOne(cascade = { CascadeType.DETACH})
	// @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
	// CascadeType.REFRESH, CascadeType.REMOVE},orphanRemoval = true)
	private PinCode pincode;

	@OneToOne
	// @OneToOne(cascade = CascadeType.ALL)
	// @OneToOne(cascade = {CascadeType.DETACH})
	// @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
	// CascadeType.REFRESH, CascadeType.REMOVE},orphanRemoval = true)
	private States states;
	// @OneToOne(cascade = CascadeType.ALL)
	// @OneToOne(cascade = {CascadeType.DETACH})
	@OneToOne
	// @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
	// CascadeType.REFRESH, CascadeType.REMOVE},orphanRemoval = true)
	private City city;

	public VendorDetails() {

	}

	@OneToOne
	public Users user;

}
