/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.main.Module.java
 * Class:			Module
 * Date:			2012-8-2
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package org.sbbs.security.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.sbbs.base.model.BaseIDEntity;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @since 2012-8-2 下午5:36:39
 */
@Entity
@Table(name = "security_module")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Module extends BaseIDEntity implements Comparable<Module> {

	/** 描述 */
	private static final long serialVersionUID = -6926690440815291509L;

	@NotBlank
	@Length(min = 1, max = 32)
	@Column(nullable = false, length = 32)
	private String name;

	/**
	 * 模块的入口地址
	 */
	@NotBlank
	@Length(min = 1, max = 255)
	@Column(nullable = false, length = 255)
	private String url;

	@Length(max = 255)
	@Column(length = 255)
	private String description;

	/**
	 * 标志符，用于授权名称（类似module:save）
	 */
	@NotBlank
	@Length(min = 1, max = 32)
	@Column(nullable = false, length = 32, unique = true, updatable = false)
	private String sn;

	/**
	 * 模块的排序号,越小优先级越高
	 */
	@NotNull
	@Range(min = 1, max = 99)
	@Column(length = 2)
	private Integer priority = 99;

	@ManyToOne
	@JoinColumn(name = "parentId")
	private Module parent;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "parent")
	@OrderBy("priority ASC")
	private List<Module> children = new ArrayList<Module>();

	/**
	 * 因为hibernate更新使用的是merge方法，会自动新增关联的瞬时对象，如果再次配置CascadeType.MERGE，会插入两条数据。<br/>
	 * 详见我的博客：<a href="ketayao.com">ketayao.com</a>
	 */
	@OneToMany(mappedBy = "module", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, orphanRemoval = true)
	private List<Permission> permissions = new ArrayList<Permission>();

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
	 * 返回 url 的值
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置 url 的值
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
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

	/**
	 * 返回 priority 的值
	 * 
	 * @return priority
	 */
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

	/**
	 * 返回 parent 的值
	 * 
	 * @return parent
	 */
	public Module getParent() {
		return parent;
	}

	/**
	 * 设置 parent 的值
	 * 
	 * @param parent
	 */
	public void setParent(Module parent) {
		this.parent = parent;
	}

	/**
	 * 返回 children 的值
	 * 
	 * @return children
	 */
	public List<Module> getChildren() {
		return children;
	}

	/**
	 * 设置 children 的值
	 * 
	 * @param children
	 */
	public void setChildren(List<Module> children) {
		this.children = children;
	}

	/**
	 * 返回 sn 的值
	 * 
	 * @return sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * 设置 sn 的值
	 * 
	 * @param sn
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 返回 permissions 的值
	 * 
	 * @return permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * 设置 permissions 的值
	 * 
	 * @param permissions
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	/*
	 * 
	 */
	@Override
	public int compareTo(Module m) {
		if (m == null) {
			return -1;
		} else if (m == this) {
			return 0;
		} else if (this.priority < m.getPriority()) {
			return -1;
		} else if (this.priority > m.getPriority()) {
			return 1;
		}

		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((sn == null) ? 0 : sn.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Module other = (Module) obj;
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
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (sn == null) {
			if (other.sn != null)
				return false;
		} else if (!sn.equals(other.sn))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Module [name=" + name + ", url=" + url + ", description="
				+ description + ", sn=" + sn + ", priority=" + priority
				+ ", parent=" + parent + "]";
	}

	/*
	 * @Override public String toString() { return
	 * Objects.toStringHelper(this).addValue(id).addValue(name) .addValue(parent
	 * == null ? null : parent.getName()) .addValue(children.size()).toString();
	 * }
	 */

}
