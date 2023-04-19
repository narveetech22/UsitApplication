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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recruiting_interiews")
@Entity
public class RecInterviews {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long intrid;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "interview_date")
	private LocalDateTime interviewdate;

	@Column(name = "timezone")
	private String timezone;

	@Column(name = "round")
	private String round;

	@Column(name = "mode")
	private String mode;

	@Column(name = "feedback")
	private String feedback;

	@Column(name = "interview_status")
	private String interviewstatus;

	@OneToOne
	@JoinColumn(name = "addedby")
	private Users users;

	@Column(name = "updated_by")
	private long updatedby;

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "created_date", nullable = false, updatable = false)
	@CreationTimestamp()
	private LocalDateTime createddate;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updateddate;

	@Column(name = "status")
	private String status = "Active";

	@OneToOne // (cascade = CascadeType.DETACH)
	@JoinColumn(name = "sub_id")
	private ReqSubmission submission;

}
