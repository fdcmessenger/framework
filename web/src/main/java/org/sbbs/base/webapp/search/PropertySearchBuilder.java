package org.sbbs.base.webapp.search;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

public class PropertySearchBuilder {
	private static final String GROUPON_FILTER_KEY = "groupon_";// and 和 or

	// ，如果没有或者都不是则默认为and
	private static final String GROUPON_OR = "or";// and 和 or

	// ，如果没有或者都不是则默认为and
	private static final String FILTER_PREFIX = "filter_";

	private static final int DEFAULT_PAGE_SIZE = 20;

	private static final int DEFAULT_PAGE_NUMBER = 0;

	private static final String PAGE_KEY = "page";

	private static final String PAGESIZE_KEY = "pagesize";

	private static final String SORTNAME_KEY = "sortname";

	private static final String SORTORDER_KEY = "sortorder";

	public static Search BuildSearch(HttpServletRequest req) {
		Search search = new Search();

		search.addFilter(builderSearchFilters(req));
		search = buildPager(req, search);
		search = buildSorts(req, search);
		return search;
	}

	private static Search buildSorts(HttpServletRequest req, Search search) {
		String sortName = req.getParameter(SORTNAME_KEY);
		String sortOrder = req.getParameter(SORTORDER_KEY);

		if (sortName != null && sortName.trim().length() > 0) {
			String[] sorts = sortName.split(",");
			for (int i = 0; i < sorts.length; i++) {
				String sort = sorts[i].trim();
				//res.setUrl(key1 + "/" + key2);
				String[] splits = sort.split(" ");
				if (splits.length == 2) {
					boolean isAsc = (splits[1] != null && splits[1].equalsIgnoreCase("asc"));
					if (isAsc) {
						search.addSortAsc(splits[0]);
					} else {
						search.addSortDesc(splits[0]);
					}

				} else if (splits.length == 1) {
					boolean isAsc = (sortOrder != null && sortOrder.equalsIgnoreCase("asc"));
					if (isAsc) {
						search.addSortAsc(splits[0]);
					} else {
						search.addSortDesc(splits[0]);
					}

				}

			}

		} else {
			search.clearSorts();
		}

		return search;
	}

	private static Search buildPager(HttpServletRequest req, Search search) {
		String page = req.getParameter(PAGE_KEY);
		String pageSize = req.getParameter(PAGESIZE_KEY);

		int pager, pagesize;

		try {
			pager = Integer.parseInt(page);

			if (pager > 0)
				pager--;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			pager = DEFAULT_PAGE_NUMBER;
		}

		try {
			pagesize = Integer.parseInt(pageSize);
		} catch (NumberFormatException e) {
			pagesize = DEFAULT_PAGE_SIZE;
		}

		search.setMaxResults(pagesize);
		search.setPage(pager);
		return search;
	}

	private static Filter builderSearchFilters(HttpServletRequest req) {
		Filter filter;
		Map<String, Object> filterParamMap = getParametersStartingWith(req, FILTER_PREFIX);
		String groupon = req.getParameter(GROUPON_FILTER_KEY);
		if (groupon != null && groupon.equalsIgnoreCase(GROUPON_OR))
			groupon = "or";
		else
			groupon = "and";

		List srList = new ArrayList();
		for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = (String) entry.getValue();
			// 如果value值为空,则忽略此filter.
			if (StringUtils.isNotBlank(value)) {
				Filter sr = buildSearchFilter(filterName, value);
				srList.add(sr);
			}
		}
		Filter[] sfilters = new Filter[srList.size()];
		srList.toArray(sfilters);

		if (groupon.equals(GROUPON_OR)) {
			filter = Filter.or(sfilters);
		} else {
			filter = Filter.and(sfilters);
		}

		return filter;
	}

	/**
	 * @param filterName
	 * @param value
	 * @return
	 */
	private static Filter buildSearchFilter(final String filterName, final String value) {

		String firstPart = StringUtils.substringBefore(filterName, "_");
		String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length());
		SearchOperator opr;
		try {
			opr = SearchOperator.valueOf(matchTypeCode.toLowerCase());

		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + ":" + matchTypeCode + "没有按规则编写,无法得到属性比较类型.", e);
		}
		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		Assert.isTrue(StringUtils.isNotBlank(propertyNameStr), "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
		Filter sr = new Filter();

		sr.setProperty(propertyNameStr);
		sr.setOperator(opr.getIntValue());
		if (opr.getIntValue() == 7 || opr.getIntValue() == 70 || opr.getIntValue() == 71 || opr.getIntValue() == 72) {
			sr.setOperator(7);
		} else {
			sr.setOperator(opr.getIntValue());
		}
		if (opr.getIntValue() == 70) {
			sr.setValue("%" + value + "%");
		} else if (opr.getIntValue() == 71) {
			sr.setValue(value + "%");
		} else if (opr.getIntValue() == 72) {
			sr.setValue("%" + value);
		} else {
			sr.setValue(value);
		}

		return sr;

	}

	/**
	 * @param request
	 * @param prefix
	 * @return
	 */
	private static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
		Assert.notNull(request, "Request must not be null");
		Assert.notNull(prefix, "filter prefix must not be null");
		Assert.hasText(prefix, "'name' must not be empty");
		Enumeration<?> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		/*
		 * if ( prefix == null ) { prefix = ""; }
		 */

		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}
}
