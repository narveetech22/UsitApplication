package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reqsubmission")
public class ReqSubmission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "submission")
	@SequenceGenerator(name = "ReqSubmission", sequenceName = "reqsub")
	@Column(name = "subId")
	private Long submissionid;

	private String ratetype;

	private double rate;
	private String relocate;
	private String expenses;
	private String benefits;

	@Column(name = "relocationAssistance")
	private String relocationAssistance;

	private String substatus = "Submitted";

	@Column(name = "status")
	private String status = "Active";

	private String endclient;

	@OneToOne // (cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }) //
				// (cascade = CascadeType.DETACH)
	private Requirements requirements;

	@OneToOne // (cascade = CascadeType.DETACH)
	private RecConsultant consultant;

	@OneToOne // (cascade = CascadeType.DETACH)
	private Users users;

	@OneToOne // (cascade = CascadeType.DETACH)
	@JoinColumn(name = "submittedby")
	private Users submittedby;

	@Column(name = "updatedby")
	private long updatedby;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createddate", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate createddate;

	@Column(name = "updateddate")
	@UpdateTimestamp
	private LocalDateTime updateddate;

}
