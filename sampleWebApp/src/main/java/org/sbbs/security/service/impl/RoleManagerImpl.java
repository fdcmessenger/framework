package org.sbbs.security.service.impl;

import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.RoleDao;
import org.sbbs.security.model.Role;
import org.sbbs.security.service.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleManager")
public class RoleManagerImpl extends BaseManagerImpl<Role, Long>
		implements RoleManager {
	RoleDao roleDao;

	@Autowired
	public RoleManagerImpl(RoleDao RoleDao) {
		super(RoleDao);
		this.roleDao = RoleDao;
	}
}
