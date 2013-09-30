package org.sbbs.security.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sbbs.base.webapp.action.BaseGridAction;
import org.sbbs.base.webapp.search.PropertySearchBuilder;
import org.sbbs.security.service.OrganizationManager;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

public class OrganizationPagedAction extends BaseGridAction {

	public String gridPageList() {
		HttpServletRequest req = this.getRequest();
		Search search = PropertySearchBuilder.BuildSearch(getRequest());
		SearchResult srt = this.organizationManager.searchAndCount(search);
		this.preGridPageResult(search, srt);
		return this.ajaxReturn.success("角色信息加载成功.");// this.SUCCESS;
	}

	private OrganizationManager organizationManager;

	public void setOrganizationManager(OrganizationManager organizationManager) {
		this.organizationManager = organizationManager;
	}

	private List fullTreeList;

	public final List getFullTreeList() {
		return fullTreeList;
	}

	/*
	 * public void setFullTreeList(List fullTreeList) { this.fullTreeList =
	 * fullTreeList; }
	 */

	public final String displayFullTree() {
		this.fullTreeList = this.organizationManager.findAll();
		return SUCCESS;
	}
}
