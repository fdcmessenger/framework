package org.sbbs.security.dao;

import org.sbbs.base.dao.IBaseDAO;
import org.sbbs.security.model.User;

public interface UserDao extends IBaseDAO<User, Long> {
	// 根据登录名查找用户
	User findByUsername(String Username);
	
	/*	// 根据实名查找用户
	User findByRealname(String realname);
	
	// 根据包含登录名(类似like)查找用户
	Page<User> findByUsernameContaining(String Username, Pageable pageable);
	
	*//**
	 * 根据组织id查找用户。
	 * 描述
	 * @param organizationId
	 * @return
	 *//*
	List<User> findByOrganizationId(Long organizationId);*/
}