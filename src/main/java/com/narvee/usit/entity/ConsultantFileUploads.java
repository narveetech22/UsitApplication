package com.narvee.usit.entity;

import java.util.List;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class ConsultantFileUploads {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long docid;
	private String filename;
	@Column(name = "consultant_id")
	private long consultantid;
}
