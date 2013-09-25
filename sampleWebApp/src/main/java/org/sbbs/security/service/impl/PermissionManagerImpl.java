package org.sbbs.security.service.impl;

import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.PermissionDao;
import org.sbbs.security.model.Permission;
import org.sbbs.security.service.PermissionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionManager")
public class PermissionManagerImpl extends BaseManagerImpl<Permission, Long>
		implements PermissionManager {
	PermissionDao permissionDao;

	@Autowired
	public PermissionManagerImpl(PermissionDao PermissionDao) {
		super(PermissionDao);
		this.permissionDao = PermissionDao;
	}
}
