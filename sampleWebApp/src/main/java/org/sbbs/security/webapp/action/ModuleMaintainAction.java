package org.sbbs.security.webapp.action;

import org.sbbs.base.webapp.action.BaseMaintainAction;
import org.sbbs.security.model.Module;
import org.sbbs.security.model.Organization;
import org.sbbs.security.service.ModuleManager;

public class ModuleMaintainAction extends BaseMaintainAction<Module, Long> {
	private ModuleManager moduleManager;
	private Module model;

	public Module getModel() {
		return model;
	}

	public void setModel(Module model) {
		this.model = model;
	}

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

	public String rebuildTree() {
		this.moduleManager.reBuildAllTree();
		return this.ajaxReturn.success("模块（module）树结构重建成功.");
	}

	public String add() {
		try {
			this.model = new Module();
			this.setEditTypeAdd();
			return SUCCESS;
		} catch (Exception e) {

			return this.ajaxReturn.error(getText("error.common", new String[] { e.getMessage() }));

		}
	}

	public String edit() {
		try {
			this.model = this.moduleManager.find(this.getId());
			this.setEditTypeEdit();
			return SUCCESS;
		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.common", new String[] { e.getMessage() }));
		}
	}
}
