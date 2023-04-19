package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "sales_submission")
@NoArgsConstructor
@AllArgsConstructor
public class SalesSubmission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long subid;

	@Column(name = "position")
	private String position;

	@Column(name = "project_location")
	private String projectlocation;

	@Column(name = "submission_rate")
	private String submissionrate;

	@Column(name = "end_client")
	private String endclient;

	@Column(name = "end_ip")
	private String partner;

	@Column(name = "vendor")
	private String vendor;

//	@OneToOne
//	private VendorDetails vendorcompany;

	@Column(name = "rate_type")
	private String ratetype;

	@Column(name = "cp_name")
	private String cpname;

	@Column(name = "cp_mobile")
	private String cpmobile;

	@Column(name = "cp_email")
	private String cpemail;

	@Column(name = "updated_by")
	private String updatedby;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "created_date", nullable = false, updatable = false)
	@CreationTimestamp()
	private LocalDate createddate;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updateddate;

	@Column(name = "status")
	private String status = "Active";

	@OneToOne // (cascade = CascadeType.DETACH)
	@JoinColumn(name = "consultant_id")
	private SalesConsultants consultant;

	@OneToOne // (cascade = CascadeType.DETACH)
	@JoinColumn(name = "user_id")
	private Users user;

	private String remarks;

}
