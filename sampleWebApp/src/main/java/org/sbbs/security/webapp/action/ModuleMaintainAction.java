package org.sbbs.security.webapp.action;

import org.sbbs.base.webapp.action.BaseMaintainAction;
import org.sbbs.security.model.Module;
import org.sbbs.security.service.ModuleManager;

public class ModuleMaintainAction extends BaseMaintainAction<Module, Long> {
	private ModuleManager moduleManager;

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

	public  String rebuildTree() {
		this.moduleManager.reBuildAllTree();
		return this.ajaxReturn.success("模块（module）树结构重建成功.");
	}
}
