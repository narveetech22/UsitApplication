package com.narvee.usit.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IRecInterviewHelper {
	// select i.id, c.name,i.interview_date,i.round,r.jobtitle,
	// i.mode,s.createddate,u.fullname,i.timezone, i.interview_status
	public long getId();

	public String getName();

	public LocalDateTime getInterview_date();

	public String getRound();

	public String getMode();

	public String getJobtitle();

	public LocalDate getCreateddate();

	public String getFullname();

	public String getInterview_status();

	public String getStatus();

	public String getTimezone();
}
