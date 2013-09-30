/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.authenticate.User.java
 * Class:			User
 * Date:			2012-8-2
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package org.sbbs.security.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.sbbs.base.model.BaseIDEntity;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @since 2012-8-2 下午2:44:58
 */
@Entity
@Table(name = "security_user")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="org.sbbs.security")
public class User extends BaseIDEntity {

	/** 描述 */
	private static final long serialVersionUID = -4277639149589431277L;

	private String realname;

	private String username;

	private String password;

	private String plainPassword;

	private String salt;

	private String phone;

	private String email;

	/**
	 * 帐号创建时间
	 */

	private Date createTime;

	/**
	 * 使用状态disabled，enabled
	 */

	private String status = "enabled";

	/*
	 * @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST,
	 * CascadeType.REMOVE }, orphanRemoval = true)
	 * 
	 * @OrderBy("priority ASC") private List<UserRole> userRoles = new
	 * ArrayList<UserRole>();
	 */

	private Organization organization;

	/**
	 * 返回 realname 的值
	 * 
	 * @return realname
	 */
	@NotBlank
	@Length(min = 1, max = 32)
	@Column(nullable = false, length = 32, updatable = false)
	public String getRealname() {
		return realname;
	}

	/**
	 * 设置 realname 的值
	 * 
	 * @param realname
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * 返回 username 的值
	 * 
	 * @return username
	 */
	@NotBlank
	@Length(min = 1, max = 32)
	@Column(nullable = false, length = 32, unique = true, updatable = false)
	public String getUsername() {
		return username;
	}

	/**
	 * 设置 username 的值
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 返回 password 的值
	 * 
	 * @return password
	 */
	@Column(nullable = false, length = 64)
	public String getPassword() {
		return password;
	}

	/**
	 * 设置 password 的值
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 返回 createTime 的值
	 * 
	 * @return createTime
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime 的值
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回 status 的值
	 * 
	 * @return status
	 */
	@NotBlank
	@Length(max = 16)
	@Column(nullable = false, length = 16)
	public String getStatus() {
		return status;
	}

	/**
	 * 设置 status 的值
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 返回 plainPassword 的值
	 * 
	 * @return plainPassword
	 */
	@Transient
	public String getPlainPassword() {
		return plainPassword;
	}

	/**
	 * 设置 plainPassword 的值
	 * 
	 * @param plainPassword
	 */
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	/**
	 * 返回 salt 的值
	 * 
	 * @return salt
	 */
	@Column(nullable = false, length = 32)
	public String getSalt() {
		return salt;
	}

	/**
	 * 设置 salt 的值
	 * 
	 * @param salt
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 返回 email 的值
	 * 
	 * @return email
	 */
	@Email
	@Length(max = 128)
	@Column(length = 128)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 email 的值
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*	*//**
	 * 返回 userRoles 的值
	 * 
	 * @return userRoles
	 */
	/*
	 * public List<UserRole> getUserRoles() { return userRoles; }
	 *//**
	 * 设置 userRoles 的值
	 * 
	 * @param userRoles
	 */
	/*
	 * public void setUserRoles(List<UserRole> userRoles) { this.userRoles =
	 * userRoles; }
	 */

	/**
	 * 返回 phone 的值
	 * 
	 * @return phone
	 */
	@Length(max = 32)
	@Column(length = 32)
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置 phone 的值
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 返回 organization 的值
	 * 
	 * @return organization
	 */
	@ManyToOne
	@JoinColumn(name = "orgId")
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * 设置 organization 的值
	 * 
	 * @param organization
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((realname == null) ? 0 : realname.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		User other = (User) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (realname == null) {
			if (other.realname != null)
				return false;
		} else if (!realname.equals(other.realname))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
		return "User [realname=" + realname + ", username=" + username + ", phone=" + phone + ", email=" + email
				+ ", createTime=" + createTime + ", status=" + status + "]";
	}

	// 在做debug测试时，可能hibernate默认会调用toString方法，该方法包装了集合的样式，在未打开sessionInView时会造成延迟加载错误，
	// @Override
	// public String toString() {
	// return ToStringBuilder.reflectionToString(this);
	// }
}
