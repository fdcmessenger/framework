package org.sbbs.security.webapp.action;

import javax.servlet.http.HttpServletRequest;

import org.sbbs.base.webapp.action.BaseGridAction;
import org.sbbs.base.webapp.search.PropertySearchBuilder;
import org.sbbs.security.service.ModuleManager;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

public class ModulePagedAction extends BaseGridAction {

	public String gridPageList() {
		HttpServletRequest req = this.getRequest();
		Search search = PropertySearchBuilder.BuildSearch(getRequest());
		SearchResult srt = this.moduleManager.searchAndCount(search);
		this.preGridPageResult(search, srt);
		return this.ajaxReturn.success("角色信息加载成功.");// this.SUCCESS;
	}

	private ModuleManager moduleManager;

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

}
