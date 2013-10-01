package org.sbbs.security.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.UserDao;
import org.sbbs.security.model.User;
import org.sbbs.security.service.UserManager;
import org.sbbs.security.shiro.ShiroDbRealm;
import org.sbbs.security.shiro.ShiroDbRealm.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userManager")
public class UserManagerImpl extends BaseManagerImpl<User, Long>
		implements UserManager {
	UserDao userDao;
	private ShiroDbRealm shiroRealm;




	@Autowired
	public void setShiroRealm(ShiroDbRealm shiroRealm) {
		this.shiroRealm = shiroRealm;
	}

	@Autowired
	public UserManagerImpl(UserDao UserDao) {
		super(UserDao);
		this.userDao = UserDao;
	}

	@Override
	public User getByUserName(String username) {
		return this.userDao.findByUsername(username);
	}
	@Override
	public void updatePwd(User user, String newPwd) throws ServiceException {
		//if (isSupervisor(user.getId())) {
		//	logger.warn("操作员{},尝试修改超级管理员用户", SecurityUtils.getSubject().getPrincipal());
		//	throw new ServiceException("不能修改超级管理员用户");
		//}
		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		boolean isMatch = ShiroDbRealm.validatePassword(user.getPlainPassword(), user.getPassword(), user.getSalt());
		if (isMatch) {
			HashPassword hashPassword = ShiroDbRealm.encryptPassword(newPwd);
			user.setSalt(hashPassword.salt);
			user.setPassword(hashPassword.password);
			userDao.save(user);
			shiroRealm.clearCachedAuthorizationInfo(user.getUsername());

			return;
		}

		throw new ServiceException("用户密码错误！");
	}
}
