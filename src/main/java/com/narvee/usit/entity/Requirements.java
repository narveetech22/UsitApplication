package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requirment")
public class Requirements {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @SequenceGenerator(name = "Requirments", sequenceName = "requirmentSeq")
	@Column(name = "id")
	private Long requirementid;

	public String reqnumber;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "postedon")
	private LocalDate postedon;

	@Column(name = "vendor")
	private String vendor;

	private String jobtitle;

	private String category;

	private String email;

	private String mobile;

	@Column(name = "jobdescription", columnDefinition = "MEDIUMTEXT")
	private String jobdescription;

	private String recruitername;

	private String location;

	private String jobexperience;

	private String jobskills;

	private String employmenttype;

	private double salary;

	private String source;

	@Column(name = "updateddate")
	@UpdateTimestamp
	private LocalDateTime updateddate;

	@Column(name = "status")
	private String status = "Active";

	@OneToOne
	@JoinColumn(name = "addedby")
	private Users users;

	@Column(name = "updatedby")
	private long updatedby;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createddate", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate createddate;

//	@OneToMany(mappedBy = "requiremnts",cascade = CascadeType.ALL)
//	private List<AssignRequirement> empid;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
	// "order")
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "requiremnts", fetch =
	// FetchType.EAGER)
	@Transient
	private Set<AssignRequirement> empid;

//	public void add(AssignRequirement item) {
//
//		if (item != null) {
//			if (empid == null) {
//				empid = new HashSet<>();
//			}
//
//			empid.add(item);
//			// item.setOrder(this);
//		}
//	}

}
