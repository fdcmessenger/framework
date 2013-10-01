package org.sbbs.security.webapp.action;

import org.hibernate.service.spi.ServiceException;
import org.sbbs.base.webapp.action.AjaxReturn;
import org.sbbs.base.webapp.action.BaseMaintainAction;
import org.sbbs.security.SecurityConstants;
import org.sbbs.security.log.LogMessageObject;
import org.sbbs.security.log.impl.LogUitl;
import org.sbbs.security.model.User;
import org.sbbs.security.service.UserManager;

public class UserMaintainAction extends BaseMaintainAction<User, Long> {

	/**
     *
     */
	private static final long serialVersionUID = 2332788763668956953L;

	public String add() {
		try {
			this.model = new User();
			this.setEditTypeAdd();
			return SUCCESS;
		} catch (Exception e) {

			return this.ajaxReturn.error(getText("error.common", new String[] { e.getMessage() }));

		}
	}

	public String edit() {
		try {
			this.model = this.userManager.find(this.getId());
			this.setEditTypeEdit();
			return SUCCESS;
		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.common", new String[] { e.getMessage() }));
		}
	}

	public String save() {
		try {
			this.userManager.save(this.getModel());
			return this.ajaxReturn.formSuccessRefreshGridCloseFormDialog(
					getText((isNew()) ? "user.added" : "user.updated", "no msg key found,save successed."),
					this.getGridId());

		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.saved", new String[] { e.getMessage() }));
		}
	}

	public String delete() {
		try {
			String[] sIds = this.getIds().split(",");

			Long[] lIds = new Long[sIds.length];
			for (int i = 0; i < sIds.length; i++) {
				lIds[i] = Long.parseLong(sIds[i]);
			}

			this.userManager.removeByIds(lIds);

			return this.ajaxReturn.success(getText((isNew()) ? "user.deleted" : "user.updated",
					"no msg key found,save successed."));

		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.deleted", new String[] { e.getMessage() }));
		}

	}

	public  String updatePassword() {
		String plainPassword=this.getRequest().getParameter("plainPassword");
		String newPassword=this.getRequest().getParameter("newPassword");
		String rPassword=this.getRequest().getParameter("rPassword");


		User user = (User)this.getRequest().getSession().getAttribute(SecurityConstants.LOGIN_USER);

		if (newPassword != null && newPassword.equals(rPassword)) {
			user.setPlainPassword(plainPassword);
			try {
				userManager.updatePwd(user, newPassword);
			} catch (ServiceException e) {
				LogUitl.putArgs(LogMessageObject.newIgnore());//忽略日志
				return ajaxReturn.error(e.getMessage());//.setCallbackType("").toString();
			}
		//	LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{user.getUsername()}));
			return ajaxReturn.success("修改密码成功！");//.toString();
		}

		return ajaxReturn.error("修改密码失败！");//.setCallbackType("").toString();
	}


	private UserManager userManager;

	// @Valid
	private User model;

	/*
	 * public UserManager getUserManager() { return userManager; }
	 */

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public User getModel() {
		return model;
	}

	public void setModel(User model) {
		this.model = model;
	}

}
