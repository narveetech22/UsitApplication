package com.narvee.usit.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface ListRecSubmissions {
//u.userid,c.id, r., c.name,u., r.createddate, r.endclient,r., r., r., r., r., r.
	public LocalDate getCreateddate();

	public long getUserid();

	public long getId();

	public long getConsultant_id();

	public long getSub_id();

	public String getName();

	public String getFullname();

	public String getRate();

	public long getSubmittedby();

	public String getSubstatus();

	public String getStatus();

	public String getEndclient();

	public String getJobtitle();
}
