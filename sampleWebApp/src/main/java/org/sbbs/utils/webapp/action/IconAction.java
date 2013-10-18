package org.sbbs.utils.webapp.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class IconAction extends ActionSupport {
	private List<String> iconNames = new ArrayList<String>();

	public String list() {

		String webPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
		String imgPath = webPath + "/scripts/dwz/themes/css/images/toolbar_icons16";

		File dir = new File(imgPath);
		File[] files = dir.listFiles();

		if (iconNames.size() == 0) {
			//iconNames = new ArrayList<String>();
			for (File file : files) {
				iconNames.add(FileUtils.getRealName(file.getName()));
			}
		}

		return this.SUCCESS;
	}

	public String reload(HttpServletRequest request, Map<String, Object> map) {
		iconNames = null;
		return list();
	}

	public List<String> getIconNames() {
		return iconNames;
	}

}
