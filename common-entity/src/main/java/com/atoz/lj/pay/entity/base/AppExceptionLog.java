package com.atoz.lj.pay.entity.base;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AppExceptionLog")
public class AppExceptionLog extends CommonEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7140875430897395340L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "message", nullable = false)
	private Blob message;

	@Column(name = "url", nullable = false)
	private String url;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Blob getMessage() {
		return message;
	}

	public void setMessage(Blob message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
