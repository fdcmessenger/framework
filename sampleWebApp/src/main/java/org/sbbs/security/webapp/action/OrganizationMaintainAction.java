package org.sbbs.security.webapp.action;

import org.sbbs.base.webapp.action.BaseMaintainAction;
import org.sbbs.security.model.Organization;
import org.sbbs.security.service.OrganizationManager;

public class OrganizationMaintainAction extends BaseMaintainAction<Organization, Long> {
	private OrganizationManager organizationManager;

	public void setOrganizationManager(OrganizationManager organizationManager) {
		this.organizationManager = organizationManager;
	}

	public  String rebuildTree() {
		this.organizationManager.reBuildAllTree();
		return this.ajaxReturn.success("组织（organization）树结构重建成功.");
	}
}
