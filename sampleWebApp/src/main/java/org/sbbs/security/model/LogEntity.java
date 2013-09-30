/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.main.LogEntity.java
 * Class:			LogEntity
 * Date:			2013-5-3
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/

package org.sbbs.security.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.sbbs.base.model.BaseIDEntity;
import org.sbbs.base.model.BaseObject;
import org.sbbs.security.log.LogLevel;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 2.1.0
 * @since 2013-5-3 下午4:43:44
 */
@Entity
@Table(name = "security_log_entity")
public class LogEntity extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/** 描述 */
	private static final long serialVersionUID = 6057051455824317181L;

	@Column(length = 255)
	private String message;

	@Column(length = 32)
	private String username;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Column(length = 16)
	private String ipAddress;

	@Column(length = 16)
	@Enumerated(EnumType.STRING)
	private LogLevel logLevel;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((logLevel == null) ? 0 : logLevel.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogEntity other = (LogEntity) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (logLevel != other.logLevel)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LogEntity [message=" + message + ", username=" + username + ", createTime=" + createTime
				+ ", ipAddress=" + ipAddress + ", logLevel=" + logLevel + "]";
	}

}
