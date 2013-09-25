package org.sbbs.security.service.impl;

import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.UserDao;
import org.sbbs.security.model.User;
import org.sbbs.security.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userManager")
public class UserManagerImpl extends BaseManagerImpl<User, Long>
		implements UserManager {
	UserDao userDao;

	@Autowired
	public UserManagerImpl(UserDao UserDao) {
		super(UserDao);
		this.userDao = UserDao;
	}

	@Override
	public User getByUserName(String username) {
		return this.userDao.findByUsername(username);
	}
}
