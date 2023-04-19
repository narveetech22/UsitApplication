package com.narvee.usit.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

public interface VmsProj {
	public Long getId();

	public String getCompany_name();

	public String getRecruiter_name();

	public String getCp_mobile();

	public String getCp_email();

	public String getHeadQuarters();

	public String getCategory();

	public String getPseudoname();

	public LocalDate getUpdated_date();

	public LocalDate getCreated_date();

	public String getStatus();
}
