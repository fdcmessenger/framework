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
import org.sbbs.base.model.BaseObject;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 2.1.0
 * @since 2013-5-6 下午2:29:52
 */
@Entity
@Table(name = "security_user_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserRole extends BaseObject {

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
	private static final long serialVersionUID = -8888778227379780116L;

	/**
	 * 值越小，优先级越高
	 */
	@NotNull
	@Range(min = 1, max = 99)
	@Column(length = 2, nullable = false)
	private Integer priority = 99;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/**
	 * 返回 role 的值
	 * 
	 * @return role
	 */
	public Role getRole() {
		return role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserRole other = (UserRole) obj;
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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	/**
	 * 设置 role 的值
	 * 
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [priority=" + priority + ", role=" + role + ", user=" + user + "]";
	}

	/**
	 * 返回 user 的值
	 * 
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 设置 user 的值
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
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

}
