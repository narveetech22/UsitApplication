package com.narvee.usit.entity;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userid;
	private String firstname;
	private String lastname;
	private String fullname;
	private String pseudoname;
	private String personalcontactnumber;
	private String companycontactnumber;
	private String email;
	private int isactive;
	private String password;
	private String designation;
	private String technology;
	private int experience;
	private String location;
	private String accounttype;
	private String status = "Active";
	private String department;
	@Column(name = "remarks", length = 400)
	private String remarks;
	@UpdateTimestamp
	private LocalDateTime updateddate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createddate", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate createddate;
	@OneToOne(cascade = CascadeType.DETACH)
	private Roles role;

}
