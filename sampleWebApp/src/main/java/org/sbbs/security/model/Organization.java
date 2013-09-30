/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.main.Organization.java
 * Class:			Organization
 * Date:			2012-8-27
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package org.sbbs.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.sbbs.base.model.BaseTreeNode;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @since 2012-8-27 下午3:25:15
 */
@Entity
@Table(name = "security_organization")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organization extends BaseTreeNode<Organization, Long> {

	/** 描述 */
	private static final long serialVersionUID = -7324011210610828114L;

	@NotBlank
	@Length(min = 1, max = 64)
	@Column(nullable = false, length = 64)
	private String name;

	@Length(max = 255)
	@Column(length = 255)
	private String description;
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "parentId") private Organization parent;
	 */

	/*
	 * @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
	 * mappedBy = "parent") private List<Organization> children = new
	 * ArrayList<Organization>();
	 */


	private List<User> users = new ArrayList<User>();


	private List<OrganizationRole> organizationRoles = new ArrayList<OrganizationRole>();

	/**
	 * 返回 name 的值
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 name 的值
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 description 的值
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 description 的值
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*	*//**
	 * 返回 parent 的值
	 * 
	 * @return parent
	 */
	/*
	 * public Organization getParent() { return parent; }
	 *//**
	 * 设置 parent 的值
	 * 
	 * @param parent
	 */
	/*
	 * public void setParent(Organization parent) { this.parent = parent; }
	 */

	/*	*//**
	 * 返回 children 的值
	 * 
	 * @return children
	 */
	/*
	 * public List<Organization> getChildren() { return children; }
	 *//**
	 * 设置 children 的值
	 * 
	 * @param children
	 */
	/*
	 * public void setChildren(List<Organization> children) { this.children =
	 * children; }
	 */

	/**
	 * 返回 users 的值
	 * 
	 * @return users
	 */
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "organization")
	public List<User> getUsers() {
		return users;
	}

	/**
	 * 设置 users 的值
	 * 
	 * @param users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@OneToMany(mappedBy = "organization")
	@OrderBy("priority ASC")
	public List<OrganizationRole> getOrganizationRoles() {
		return organizationRoles;
	}

	public void setOrganizationRoles(List<OrganizationRole> organizationRoles) {
		this.organizationRoles = organizationRoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		//result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
		Organization other = (Organization) obj;
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
		/*
		 * if (parent == null) { if (other.parent != null) return false; } else
		 * if (!parent.equals(other.parent)) return false;
		 */
		return true;
	}

	@Override
	public String toString() {
		return "Organization [name=" + name + ", description=" + description
		// + ", parent=" + parent
				+ "]";
	}

	/*
	 * @Override public String toString() { return
	 * Objects.toStringHelper(this).addValue(id).addValue(name) .addValue(parent
	 * == null ? null : parent.getName()) // .addValue(children.size())
	 * .toString(); }
	 */

}
