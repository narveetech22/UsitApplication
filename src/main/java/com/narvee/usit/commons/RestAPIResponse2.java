package com.narvee.usit.commons;

import java.time.LocalDateTime;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.annotation.JsonFormat;

@ConfigurationProperties(prefix = "rest.api.response")
@PropertySource(value = "classpath:application.yml")
public class RestAPIResponse2 {

	public String status;
	public String message;
	public int size;
	public Object data;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "hi_IN", timezone = "IST")
	private LocalDateTime timeStamp;

	private RestAPIResponse2() {
		timeStamp = LocalDateTime.now();
	}

	public RestAPIResponse2(String status) {
		this();
		this.status = status;
	}

	public RestAPIResponse2(String status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	public RestAPIResponse2(String status, String message, int size, Object data) {
		this();
		this.status = status;
		this.size = size;
		this.message = message;
		this.data = data;
	}

}
