package org.sbbs.security.webapp.action;

import javax.servlet.http.HttpServletRequest;

import org.sbbs.base.webapp.action.BaseGridAction;
import org.sbbs.base.webapp.search.PropertySearchBuilder;
import org.sbbs.security.log.Log;
import org.sbbs.security.service.UserManager;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

public class UserPagedAction extends BaseGridAction {
	@Log(message = "列表显示用户的信息。")
	public String gridPageList() {
		HttpServletRequest req = this.getRequest();
		Search search = PropertySearchBuilder.BuildSearch(getRequest());
		SearchResult srt = this.userManager.searchAndCount(search);
		this.preGridPageResult(search, srt);
		return this.ajaxReturn.success("用户信息加载成功.");// this.SUCCESS;
	}

	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
