package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Data
@Table(name = "tbl_sales_consultant")
public class SalesConsultants {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long sid;

	@Column(name = "name")
	private String name;

	@Column(name = "email", length = 20)
	private String email;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "experience")
	private String experience;

	@Column(name = "location")
	private String location;

	@Column(name = "relocate")
	private String relocate;

	@Column(name = "skills", columnDefinition = "TEXT")
	private String skills;

	@Column(name = "rate_type")
	private String ratetype;

	@Column(name = "rate")
	private String hourlyrate;

	@Column(name = "summary", columnDefinition = "MEDIUMTEXT")
	private String summary;

	@Column(name = "resume")
	private String resumepath;

	@Column(name = "h1b_copy")
	private String h1bcopy;

	@Column(name = "dl_copy")
	private String dlcopy;

	@Column(name = "priority")
	private String priority;

	@Column(name = "relocateval")
	private String relocateVal;

	@Column(name = "any_restriction", columnDefinition = "MEDIUMTEXT")
	private String restriction;

	@Column(name = "status")
	private String status = "Active";

	@OneToOne
	@JoinColumn(name = "visa_status")
	private Visa visa;

	@OneToOne
	@JoinColumn(name = "technology")
	private Technologies technology;

	@OneToOne
	@JoinColumn(name = "added_by")
	private Users users;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updateddate;

	@Column(name = "updated_by")
	private long updatedby;

//	@Transient
//	 private MultipartFile resume;
//	@Transient
//	 private MultipartFile h1b;
//	@Transient
//	 private MultipartFile dl;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "created_date", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate createddate;
}
