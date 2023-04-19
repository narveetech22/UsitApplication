package com.narvee.usit.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class AssignRequirement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userid;
	private Long reqid;
	private String fullname;
//	 @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "requirementid")
//	private Requirements requiremnts;

}
