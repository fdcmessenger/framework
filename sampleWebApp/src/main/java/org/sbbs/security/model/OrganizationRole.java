/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.main.OrganizationRole.java
 * Class:			OrganizationRole
 * Date:			2013-4-15
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/

package org.sbbs.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Range;
import org.sbbs.base.model.BaseIDEntity;
import org.sbbs.base.model.BaseObject;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 2.0.0
 * @since 2013-4-15 下午4:01:34
 */
@Entity
@Table(name = "security_organization_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="org.sbbs.security")
public class OrganizationRole extends BaseIDEntity {

	/** 描述 */
	private static final long serialVersionUID = -2216187629501296831L;

	/**
	 * 值越小，优先级越高
	 */

	private Integer priority = 99;

	private Role role;

	private Organization organization;

	/**
	 * 返回 role 的值
	 * 
	 * @return role
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	public Role getRole() {
		return role;
	}

	/**
	 * 设置 role 的值
	 * 
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationId")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * 返回 priority 的值
	 * 
	 * @return priority
	 */
	@NotNull
	@Range(min = 1, max = 99)
	@Column(length = 2, nullable = false)
	public Integer getPriority() {
		return priority;
	}

	/**
	 * 设置 priority 的值
	 * 
	 * @param priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		OrganizationRole other = (OrganizationRole) obj;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrganizationRole [priority=" + priority + ", role=" + role + ", organization=" + organization + "]";
	}

}
