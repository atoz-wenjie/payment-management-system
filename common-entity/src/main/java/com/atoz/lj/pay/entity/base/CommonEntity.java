package com.atoz.lj.pay.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CommonEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -304420707722746096L;

	@Column(name = "createDate",nullable = false)
	protected Date createDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "createById")
	protected User createById;
	
	@Column(name = "updateDate")
	protected String updateDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "updateById")
	protected User updateById;
	
	@Column(name = "isDeleted")
	protected boolean isDeleted;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public User getCreateById() {
		return createById;
	}

	public void setCreateById(User createById) {
		this.createById = createById;
	}

	public User getUpdateById() {
		return updateById;
	}

	public void setUpdateById(User updateById) {
		this.updateById = updateById;
	}
	
}
