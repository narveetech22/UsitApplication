package com.narvee.usit.helper;

import java.time.LocalDate;

public interface ListVendor {
	public long getId();

	public String getCompany();

	public String getLocation1();

	public String getLocation2();

	public String getCityname();

	public String getStatename();

	public String getPincode();

	public String getFullname();

	public String getFedid();

	public String getVendortype();

	public String getTyretype();

	public LocalDate getCreateddate();

	public String getVms_stat();

	public String getStatus();
}
