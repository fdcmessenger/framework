package org.sbbs.security.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.sbbs.base.service.impl.BaseTreeManagerImpl;
import org.sbbs.security.dao.ModuleDao;
import org.sbbs.security.dao.PermissionDao;
import org.sbbs.security.model.Module;
import org.sbbs.security.model.Permission;
import org.sbbs.security.model.RolePermission;
import org.sbbs.security.service.ModuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("moduleManager")
public class ModuleManagerImpl extends BaseTreeManagerImpl<Module, Long> implements ModuleManager {
	ModuleDao moduleDao;
	PermissionDao permissionDao;

	@Autowired
	public ModuleManagerImpl(ModuleDao dao) {
		super(dao);
		this.moduleDao = dao;
	}

	@Autowired
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	@Override
	public void insertModule(Module module) {
		Module parentModule = moduleDao.get(module.getParentNode().getId());
		module.setParentNode(parentModule);
		List<Permission> permissions = module.getPermissions();
		module.setPermissions(null);
		moduleDao.save(module);

		if (permissions != null) {
			for (Permission permission : permissions) {

				if (permission.getId() != null && permission.getShortName() == null)
					this.permissionDao.removeById(permission.getId());// (entity)
				else {
					permission.setModule(module);
					this.permissionDao.save(permission);
				}
			}
		}

		/*
		 * if (parentModule == null) { return AjaxObject.newError("添加模块失败：id=" +
		 * module.getParent().getId() + "的父模块不存在！").toString(); }
		 */

	/*	for (Permission permission : module.getPermissions()) {
			if (StringUtils.isNotBlank(permission.getShortName())) {
				permissions.add(permission);
			}
		}

		for (Permission permission : permissions) {
			permission.setModule(module);
		}
		module.setPermissions(permissions);*/

	}

	@Override
	public void updateModule(Module module) {
		Module oldModule = moduleDao.get(module.getId());
		oldModule = moduleDao.get(module.getId());
		oldModule.setName(module.getName());
		oldModule.setPriority(module.getPriority());
		oldModule.setDescription(module.getDescription());
		oldModule.setSn(module.getSn());
		oldModule.setUrl(module.getUrl());
		oldModule.setParentNode(module.getParentNode());
		moduleDao.save(oldModule);

		if (module.getPermissions() != null) {
			for (Permission permission : module.getPermissions()) {

				if (permission.getId() != null && permission.getShortName() == null)
					this.permissionDao.removeById(permission.getId());// (entity)
				else {
					permission.setModule(oldModule);
					this.permissionDao.save(permission);
				}
			}
		}
	}
	/*
	 * // 模块自定义权限，删除过后新增报错，会有validate的验证错误。hibernate不能copy到permission的值，导致。 for
	 * (Permission permission : module.getPermissions()) { if
	 * (StringUtils.isNotBlank(permission.getShortName())) {// 已选中的 if
	 * (permission.getId() == null) {// 新增 permission.setModule(oldModule);
	 * oldModule.getPermissions().add(permission);
	 * permissionDao.save(permission); } } else {// 未选中的 if (permission.getId()
	 * != null) {// 删除 for (Permission oldPermission :
	 * oldModule.getPermissions()) { if
	 * (oldPermission.getId().equals(permission.getId())) {
	 * oldPermission.setModule(null); permission = oldPermission; break; } }
	 * permissionDao.removeById(permission.getId());
	 * oldModule.getPermissions().remove(permission); } } }
	 */

}
