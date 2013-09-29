/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.Role.java
 * Class:			Role
 * Date:			2012-6-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package org.sbbs.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.sbbs.base.model.BaseIDEntity;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-6-7 上午11:07:45 
 */
@Entity
@Table(name="security_role")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.ketayao.ketacustom.entity.main")
public class Role extends BaseIDEntity {
	
	/** 描述  */
	private static final long serialVersionUID = -5537665695891354775L;
	
	@NotBlank
	@Length(min=1, max=32)
	@Column(nullable=false, length=32)
	private String name;
	
	@Length(max=255)
	@Column(length=255)
	private String description;
	
/*	@OneToMany(mappedBy="role", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	@OrderBy("priority ASC")
	private List<UserRole> userRoles = new ArrayList<UserRole>(0);
	
	@OneToMany(mappedBy="role", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	@OrderBy("priority ASC")
	private List<OrganizationRole> organizationRoles = new ArrayList<OrganizationRole>();
	
	@OneToMany(mappedBy="role", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	private List<RolePermission> rolePermissions = new ArrayList<RolePermission>();*/
	
	/**  
	 * 返回 name 的值   
	 * @return name  
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 设置 name 的值  
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**  
	 * 返回 description 的值   
	 * @return description  
	 */
	public String getDescription() {
		return description;
	}

	/**  
	 * 设置 description 的值  
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

/*	*//**  
	 * 返回 userRoles 的值   
	 * @return userRoles  
	 *//*
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	*//**  
	 * 设置 userRoles 的值  
	 * @param userRoles
	 *//*
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	*//**  
	 * 返回 organizationRoles 的值   
	 * @return organizationRoles  
	 *//*
	public List<OrganizationRole> getOrganizationRoles() {
		return organizationRoles;
	}

	*//**  
	 * 设置 organizationRoles 的值  
	 * @param organizationRoles
	 *//*
	public void setOrganizationRoles(List<OrganizationRole> organizationRoles) {
		this.organizationRoles = organizationRoles;
	}

	*//**  
	 * 返回 rolePermissions 的值   
	 * @return rolePermissions  
	 *//*
	public List<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	*//**  
	 * 设置 rolePermissions 的值  
	 * @param rolePermissions
	 *//*
	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}*/
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", description=" + description + "]";
	}
}
