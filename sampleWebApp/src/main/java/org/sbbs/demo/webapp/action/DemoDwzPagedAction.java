package org.sbbs.demo.webapp.action;

import javax.servlet.http.HttpServletRequest;

import org.sbbs.base.webapp.action.BaseGridAction;
import org.sbbs.base.webapp.search.PropertySearchBuilder;
import org.sbbs.demo.service.DemoEntityManager;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

public class DemoDwzPagedAction extends BaseGridAction {

	public String gridPageList() {
		HttpServletRequest req = this.getRequest();
		Search search = PropertySearchBuilder.BuildSearch(getRequest());
		SearchResult srt = this.demoEntityManager.searchAndCount(search);
	/*	this.setPageList(srt.getResult());
		this.records = srt.getTotalCount();
		this.total = getTotlePage(records, search.getMaxResults());
		this.page = search.getPage() + 1;*/
		this.preGridPageResult(search, srt);
		return this.ajaxReturn
				.success("load success,dwz message test.");// this.SUCCESS;
	}

	private DemoEntityManager demoEntityManager;

/*	public DemoEntityManager getDemoEntityManager() {
		return demoEntityManager;
	}*/

	public void setDemoEntityManager(DemoEntityManager demoEntityManager) {
		this.demoEntityManager = demoEntityManager;
	}

}
