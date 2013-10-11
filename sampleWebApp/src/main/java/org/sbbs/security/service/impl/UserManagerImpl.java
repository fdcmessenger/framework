package org.sbbs.security.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.UserDao;
import org.sbbs.security.exception.ExistedException;
import org.sbbs.security.model.User;
import org.sbbs.security.service.UserManager;
import org.sbbs.security.shiro.ShiroDbRealm;
import org.sbbs.security.shiro.ShiroDbRealm.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userManager")
public class UserManagerImpl extends BaseManagerImpl<User, Long> implements UserManager {
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

	/**
	 * 
	 * @param user
	 * @throws ExistedException
	 */
	public void insertUser(User user) throws ExistedException {
		if (userDao.findByUsername(user.getUsername()) != null) {
			throw new ExistedException("用户添加失败，登录名：" + user.getUsername() + "已存在。");
		}

		/*
		 * if (userDAO.findByRealname(user.getRealname()) != null) { throw new
		 * ExistedException("用户添加失败，真实名：" + user.getRealname() + "已存在。"); }
		 */

		// 设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		if (StringUtils.isNotBlank(user.getPlainPassword()) && shiroRealm != null) {
			HashPassword hashPassword = ShiroDbRealm.encryptPassword(user.getPlainPassword());
			user.setSalt(hashPassword.salt);
			user.setPassword(hashPassword.password);
		}
		user.setCreateTime(new Date());
		userDao.save(user);
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
	}

	/**
	 * @param user
	 */
	public void updateUser(User user) {
		User puser = this.userDao.find(user.getId());

		puser.setEmail(user.getEmail());
		puser.setOrganization(user.getOrganization());
		puser.setPhone(user.getPhone());
		puser.setStatus(user.getStatus());

		userDao.save(puser);
		shiroRealm.clearCachedAuthorizationInfo(puser.getUsername());
	}

	@Override
	public void updatePwd(User user, String newPwd) throws ServiceException {
		// if (isSupervisor(user.getId())) {
		// logger.warn("操作员{},尝试修改超级管理员用户",
		// SecurityUtils.getSubject().getPrincipal());
		// throw new ServiceException("不能修改超级管理员用户");
		// }
		// 设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		boolean isMatch = ShiroDbRealm.validatePassword(user.getPlainPassword(), user.getPassword(), user.getSalt());
		if (isMatch) {
			/*
			 * HashPassword hashPassword = ShiroDbRealm.encryptPassword(newPwd);
			 * user.setSalt(hashPassword.salt);
			 * user.setPassword(hashPassword.password); userDao.save(user);
			 * shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
			 */
			savePsw(user, newPwd);
			return;
		}

		throw new ServiceException("用户密码错误！");
	}

	public void restPsw(User user, String restPsw) {
		savePsw(user, restPsw);
	}

	private void savePsw(User user, String newPwd) {
		HashPassword hashPassword = ShiroDbRealm.encryptPassword(newPwd);
		user.setSalt(hashPassword.salt);
		user.setPassword(hashPassword.password);
		userDao.save(user);
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());

		return;
	}
}
