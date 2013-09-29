package org.sbbs.security.webapp.action;

import org.sbbs.base.webapp.action.BaseMaintainAction;
import org.sbbs.demo.model.DemoEntity;
import org.sbbs.security.service.ModuleManager;

public class ModuleMaintainAction extends BaseMaintainAction<DemoEntity, Long> {
	private ModuleManager moduleManager;

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

	public final String rebuildTree() {
		this.moduleManager.reBuildAllTree();
		return this.ajaxReturn.success("模块（module）树结构重建成功.");
	}
}
