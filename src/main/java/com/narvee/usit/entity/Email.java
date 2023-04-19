package com.narvee.usit.entity;

import java.sql.Blob;
import java.time.LocalDate;

import javax.mail.internet.MimeMultipart;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "email1")
public class Email {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @SequenceGenerator(name="email", sequenceName = "email_seq",
	// allocationSize=500)
	private Long id;

	@Column(name = "mailsubject")
	private String subject;

	@Column(name = "body", columnDefinition = "MEDIUMTEXT")
	private String body;

	@Lob
	private Blob data2;

	@Column(name = "mailfrom")
	private String from;

	@Column(name = "mail_to")
	private String to;

	@Column(name = "mail_cc")
	private String cc;

	@Column(name = "attachment", columnDefinition = "TEXT")
	private String attachment;

	private String subjectcategory;

	private String company;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "created_date", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate createddate;

}
