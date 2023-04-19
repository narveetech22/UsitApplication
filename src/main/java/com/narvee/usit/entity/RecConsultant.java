package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RecruitingConsultant")
public class RecConsultant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RecruitingConsultant")
	@SequenceGenerator(name = "RecruitingConsultant", sequenceName = "RecruitingConsultantSeq")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;

	@Column(name = "experience")
	private String experience;

	@Column(name = "rate_type")
	private String ratetype;

	@Column(name = "rate")
	private String rate;

	@Column(name = "location")
	private String location;

	@Column(name = "skills")
	private String skills;

	@Column(name = "summary", columnDefinition = "MEDIUMTEXT")
	private String summary;

	@Column(name = "resume")
	private String resume;

	private String companyname;

	private String salespersonname;

	private String companymobile;

	private String companyemail;

	@OneToOne // (cascade = CascadeType.DETACH)
	@JoinColumn(name = "visaid")
	private Visa visa;

	@OneToOne // (cascade = CascadeType.DETACH)
	@JoinColumn(name = "addedby")
	private Users users;

	@Column(name = "updatedby")
	private long updatedby;

	@OneToOne // (cascade = CascadeType.DETACH)
	@JoinColumn(name = "techid")
	private Technologies technology;

	private String remarks;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createddate", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate createddate;

	@Column(name = "updateddate")
	@UpdateTimestamp
	private LocalDateTime updateddate;

	@Column(name = "status")
	private String status = "Active";

	private String linkedin;
	private String passportnumber;
	private String projectavailabity;
	/*
	 * @JsonSerialize(using = LocalDateTimeSerializer.class)
	 * 
	 * @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	 * 
	 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	 * 
	 * @Column(name = "dayone") private LocalDateTime dayone;
	 * 
	 * @JsonSerialize(using = LocalDateTimeSerializer.class)
	 * 
	 * @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	 * 
	 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	 * 
	 * @Column(name = "daytwo") private LocalDateTime daytwo;
	 * 
	 * @JsonSerialize(using = LocalDateTimeSerializer.class)
	 * 
	 * @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	 * 
	 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	 * 
	 * @Column(name = "daythree") private LocalDateTime daythree;
	 */

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "consultant_id")
	private List<ConsultantFileUploads> fileupload;

	private String availability;

	@OneToOne // (cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }) //
	// (cascade = CascadeType.DETACH)
	private Requirements requirements;

	//
	private String qualification;
	private String qualification1;

	private String specialization;
	private String specialization1;

	private String university;
	private String university1;

	private String yop;
	private String yop1;

}
