package org.sbbs.base.webapp.action;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

public abstract class BaseGridAction extends BaseDwzAction {
	public abstract String gridPageList();

	public List getPageList() {
		return pageList;
	}

	public void setPageList(List pageList) {
		this.pageList = pageList;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getRecords() {
		return records;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	protected Integer page = 0;

	protected Integer records = 0;

	protected Integer total = 0;

	protected List pageList;

	private int getTotlePage(int recs, int pn) {
		return (((double) recs / (double) pn) > (recs / pn) ? recs / pn + 1
				: recs / pn);

	}

	protected void preGridPageResult(Search s, SearchResult<?> srt) {
		this.pageList = srt.getResult();
		this.records = srt.getTotalCount();
		this.total = getTotlePage(records, s.getMaxResults());
		this.page = s.getPage() + 1;
	}
}
