package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "technologies")
@NoArgsConstructor
@AllArgsConstructor
public class Technologies {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // , generator = "Technologies")
	// @SequenceGenerator(name = "Technologies", sequenceName = "Technologies_seq")
	private Long id;
	private String technologyarea;

	@Column(name = "listofkeyword", length = 2000)
	private String listofkeyword;

	@Column(name = "comments", length = 1500)
	private String comments;

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

	@Column(name = "remarks", length = 255)
	private String remarks;

	public Technologies(Long id, String technologyarea, String listofkeyword, String comments, String status,
			LocalDate createddate, String remarks) {
		this.id = id;
		this.technologyarea = technologyarea;
		this.listofkeyword = listofkeyword;
		this.comments = comments;
		this.status = status;
		this.createddate = createddate;
		this.remarks = remarks;
	}

}
