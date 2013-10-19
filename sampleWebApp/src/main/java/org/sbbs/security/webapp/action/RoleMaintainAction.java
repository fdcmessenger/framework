package org.sbbs.security.webapp.action;

import java.util.List;

import org.sbbs.base.webapp.action.BaseMaintainAction;
import org.sbbs.security.model.Module;
import org.sbbs.security.model.Permission;
import org.sbbs.security.model.Role;
import org.sbbs.security.model.RolePermission;
import org.sbbs.security.service.ModuleManager;
import org.sbbs.security.service.RoleManager;

public class RoleMaintainAction extends BaseMaintainAction<Role, Long> {

	public String add() {
		try {
			this.model = new Role();
			this.modulePermissionTree = buildMPTree(this.moduleManager.getAllRoots().get(0), model, new Integer[] { 0 });
			modulePermissionTree = modulePermissionTree.substring(0, modulePermissionTree.length() - 1);
			this.setEditTypeAdd();
			return SUCCESS;
		} catch (Exception e) {

			return this.ajaxReturn.error(getText("error.common", new String[] { e.getMessage() }));

		}
	}

	public String edit() {
		try {
			this.model = this.roleManager.find(this.getId());
			this.modulePermissionTree = buildMPTree(this.moduleManager.getAllRoots().get(0), model, new Integer[] { 0 });
			modulePermissionTree = modulePermissionTree.substring(0, modulePermissionTree.length() - 1);
			this.setEditTypeEdit();
			return SUCCESS;
		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.common", new String[] { e.getMessage() }));
		}
	}

	public String save() {
		try {
			if (this.getModel().getId() == null)
				this.roleManager.insertRole(getModel());
			else
				this.roleManager.updateRole(getModel());

			// this.roleManager.save(this.getModel());

			return this.ajaxReturn.formSuccessRefreshGridCloseFormDialog(
					getText((isNew()) ? "role.added" : "role.updated", "no msg key found,save successed."),
					this.getGridId());

		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.saved", new String[] { e.getMessage() }));
		}
	}

	private String buildMPTree(Module module, Role role, Integer[] moduleIndex) {
		StringBuilder explandBuilder = new StringBuilder("<span class='inputValueRole'>");
		//explandBuilder.append("<span class='inputValueRole'>");
		for (Permission permission : module.getPermissions()) {
			explandBuilder.append(permission.getName() + "<input type='checkbox' name='model.rolePermissions["
					+ moduleIndex[0] + "].permission.id' value='" + permission.getId() + "' ");
			if (role != null) {
				boolean isFind = false;
				for (RolePermission rolePermission : role.getRolePermissions()) {
					if (rolePermission.getPermission().getId().equals(permission.getId())) {
						explandBuilder.append("checked='checked'/>");
						explandBuilder.append("<input type='hidden' name='model.rolePermissions[" + moduleIndex[0]
								+ "].id' value='" + rolePermission.getId() + "'/>");
						isFind = true;
						break;
					}
				}

				if (isFind == false) {
					explandBuilder.append("/>");
				}
			}
			moduleIndex[0] = moduleIndex[0] + 1;
		}
		explandBuilder.append("</span>");

		StringBuilder builder = new StringBuilder();
		long pid = module.getParent() == null ? 0 : module.getParentNode().getId();
		builder.append("{id:" + module.getId() + ", pId:" + pid + ", name:\"" + module.getName() + "\", expland:\""
				+ explandBuilder.toString() + "\"},");

		List<Module> subModule = this.moduleManager.getImmediateDescendant(module.getId());

		for (Module o : subModule) {
			builder.append(buildMPTree(o, role, moduleIndex));
		}
		// System.out.println(builder.toString());
		String mpt = builder.toString();
		// mpt = mpt.substring(0, mpt.length() - 1);
		// System.out.println(mpt);
		return mpt;
	}

	private RoleManager roleManager;
	private ModuleManager moduleManager;
	private Role model;
	private List<Module> moduleList;

	private String modulePermissionTree;

	public Role getModel() {
		return model;
	}

	public void setModel(Role model) {
		this.model = model;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

	public String getModulePermissionTree() {
		return modulePermissionTree;
	}

}
