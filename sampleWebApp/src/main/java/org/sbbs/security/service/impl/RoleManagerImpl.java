package org.sbbs.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.RoleDao;
import org.sbbs.security.dao.RolePermissionDao;
import org.sbbs.security.model.Role;
import org.sbbs.security.model.RolePermission;
import org.sbbs.security.service.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("roleManager")
public class RoleManagerImpl extends BaseManagerImpl<Role, Long> implements RoleManager {
	RoleDao roleDao;
	RolePermissionDao rolePermissionDao;

	@Autowired
	public RoleManagerImpl(RoleDao RoleDao) {
		super(RoleDao);
		this.roleDao = RoleDao;
	}

	@Autowired
	public void setRolePermissionDao(RolePermissionDao rolePermissionDao) {
		this.rolePermissionDao = rolePermissionDao;
	}

	@Override
	public void insertRole(Role role) {
		List<RolePermission> rolePermissions = Lists.newArrayList();
		for (RolePermission rolePermission : role.getRolePermissions()) {
			if (rolePermission.getPermission() != null && rolePermission.getPermission().getId() != null) {
				rolePermissions.add(rolePermission);
			}
		}

		for (RolePermission rolePermission : rolePermissions) {
			rolePermission.setRole(role);
		}

		role.setRolePermissions(null);

		this.roleDao.save(role);
		RolePermission[] rps = new RolePermission[rolePermissions.size()];// ()

		this.rolePermissionDao.save(rolePermissions.toArray(rps));

	}

	@Override
	public void updateRole(Role role) {
		Role oldRole = this.roleDao.find(role.getId());

		if (oldRole.getRolePermissions() != null)
			rolePermissionDao.remove(oldRole.getRolePermissions());
		oldRole = this.roleDao.find(role.getId());
		oldRole.setName(role.getName());
		oldRole.setDescription(role.getDescription());
		roleDao.save(oldRole);

		List<RolePermission> rolePermissions = Lists.newArrayList();
		if (role.getRolePermissions() != null) {
			for (RolePermission rolePermission : role.getRolePermissions()) {
				if (rolePermission!=null && rolePermission.getPermission() != null && rolePermission.getPermission().getId() != null) {
					rolePermissions.add(rolePermission);
				}
			}

			for (RolePermission rolePermission : rolePermissions) {
				rolePermission.setRole(oldRole);
			}

			RolePermission[] rps = new RolePermission[rolePermissions.size()];// ()

			this.rolePermissionDao.save(rolePermissions.toArray(rps));
		}
	}
}
