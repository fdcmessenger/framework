/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.main.RolePermission.java
 * Class:			RolePermission
 * Date:			2013-4-16
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/

package org.sbbs.security.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.sbbs.base.model.BaseIDEntity;
import org.sbbs.base.model.BaseObject;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 2.0.0
 * @since 2013-4-16 下午1:47:51
 */
@Entity
@Table(name = "security_role_permission")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="org.sbbs.security")
public class RolePermission extends  BaseIDEntity{

	/** 描述 */
	private static final long serialVersionUID = -7679139844716398059L;

	private Role role;

	private Permission permission;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid")
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
	@JoinColumn(name = "permissionid", nullable = true)
	public Permission getPermission() {
		return permission;
	}

	/**
	 * 设置 permission 的值
	 * 
	 * @param permission
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePermission other = (RolePermission) obj;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "RolePermission [role=" + role + ", permission=" + permission + "]";
	}
}
