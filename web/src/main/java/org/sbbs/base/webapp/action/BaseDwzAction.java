package org.sbbs.base.webapp.action;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.Dispatcher;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationManager;
import com.opensymphony.xwork2.config.RuntimeConfiguration;
import com.opensymphony.xwork2.config.entities.ActionConfig;

/**
 * @author Administrator
 */
public class BaseDwzAction extends ActionSupport implements Preparable {
	/**
	 *
	 */
	private static final long serialVersionUID = -6789529022826198177L;

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	@Override
	public void prepare() throws Exception { // TODO Auto-generated method stub
		// printAllStruts2Actions();
	}

	public void printAllStruts2Actions() {
		Dispatcher dispatcher = Dispatcher.getInstance();
		ConfigurationManager cm = dispatcher.getConfigurationManager();

		Configuration cf = cm.getConfiguration();

		RuntimeConfiguration rc = cf.getRuntimeConfiguration();

		Map<String, Map<String, ActionConfig>> d = rc.getActionConfigs();
		Set nc = d.keySet();
		for (String key1 : d.keySet()) {
			if (!key1.equalsIgnoreCase("")
					&& !key1.equalsIgnoreCase("/config-browser")) {
				System.out.println(key1);
				Map<String, ActionConfig> a = d.get(key1);
				for (String key2 : a.keySet()) {
					System.out.println(key2);
					ActionConfig ac = a.get(key2);
				}
			}
		}
	}

	protected AjaxReturn ajaxReturn = new AjaxReturn();

	public AjaxReturn getAjaxReturn() {
		return ajaxReturn;
	}

}
