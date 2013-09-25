package org.sbbs.security.service.impl;

import java.util.List;

import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.UserRoleDao;
import org.sbbs.security.model.UserRole;
import org.sbbs.security.service.UserRoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRoleManager")
public class UserRoleManagerImpl extends BaseManagerImpl<UserRole, Long>
		implements UserRoleManager {
	UserRoleDao userRoleDao;

	@Autowired
	public UserRoleManagerImpl(UserRoleDao userRoleDao) {
		super(userRoleDao);
		this.userRoleDao = userRoleDao;
	}

	@Override
	public List<UserRole> findByUserId(Long userId) {
		return this.userRoleDao.findByUserId(userId);
	}
}
