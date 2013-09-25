package org.sbbs.security.service.impl;

import java.util.List;

import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.RolePermissionDao;
import org.sbbs.security.model.RolePermission;
import org.sbbs.security.service.RolePermissionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rolePermissionManager")
public class RolePermissionManagerImpl extends BaseManagerImpl<RolePermission, Long> implements RolePermissionManager {
	RolePermissionDao rolePermissionDao;

	@Autowired
	public RolePermissionManagerImpl(RolePermissionDao RolePermissionDao) {
		super(RolePermissionDao);
		this.rolePermissionDao = RolePermissionDao;
	}

	@Override
	public List<RolePermission> findByRoleId(Long roleId) {
		return this.rolePermissionDao.findByRoleId(roleId);
	}
}
