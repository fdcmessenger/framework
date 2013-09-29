package org.sbbs.security.webapp.action;

import javax.servlet.http.HttpServletRequest;

import org.sbbs.base.webapp.action.BaseGridAction;
import org.sbbs.base.webapp.search.PropertySearchBuilder;
import org.sbbs.security.service.RoleManager;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

public class RolePagedAction extends BaseGridAction {

	public String gridPageList() {
		HttpServletRequest req = this.getRequest();
		Search search = PropertySearchBuilder.BuildSearch(getRequest());
		SearchResult srt = this.roleManager.searchAndCount(search);
		this.preGridPageResult(search, srt);
		return this.ajaxReturn.success("角色信息加载成功.");// this.SUCCESS;
	}

	private RoleManager roleManager;

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

}
