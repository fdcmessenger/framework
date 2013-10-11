package org.sbbs.security.webapp.action;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.sbbs.base.webapp.action.BaseMaintainAction;
import org.sbbs.security.SecurityConstants;
import org.sbbs.security.log.LogMessageObject;
import org.sbbs.security.log.impl.LogUitl;
import org.sbbs.security.model.Role;
import org.sbbs.security.model.User;
import org.sbbs.security.model.UserRole;
import org.sbbs.security.service.RoleManager;
import org.sbbs.security.service.UserManager;
import org.sbbs.security.service.UserRoleManager;

import com.google.common.collect.Lists;

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
			if (this.getModel().getId() == null)
				this.userManager.insertUser(getModel());
			else
				this.userManager.updateUser(getModel());// save(this.getModel());

			return this.ajaxReturn.formSuccessRefreshGridCloseFormDialog(
					getText((isNew()) ? "user.added" : "user.updated", "no msg key found,save successed."),
					this.getGridId());

		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.saved", new String[] { e.getMessage() }));
		}
	}

	public String delete() {
		try {

			this.userManager.removeById(this.getId());
			return this.ajaxReturn.success(getText((isNew()) ? "user.deleted" : "user.updated",
					"no msg key found,save successed."));

		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.deleted", new String[] { e.getMessage() }));
		}
	}

	public String deletes() {
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

	public String updatePassword() {
		String plainPassword = this.getRequest().getParameter("plainPassword");
		String newPassword = this.getRequest().getParameter("newPassword");
		String rPassword = this.getRequest().getParameter("rPassword");

		User user = (User) this.getRequest().getSession().getAttribute(SecurityConstants.LOGIN_USER);

		if (newPassword != null && newPassword.equals(rPassword)) {
			user.setPlainPassword(plainPassword);
			try {
				userManager.updatePwd(user, newPassword);
			} catch (ServiceException e) {
				LogUitl.putArgs(LogMessageObject.newIgnore());// 忽略日志
				return ajaxReturn.error(e.getMessage());// .setCallbackType("").toString();
			}
			// LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new
			// Object[]{user.getUsername()}));
			return ajaxReturn.success("修改密码成功！");// .toString();
		}

		return ajaxReturn.error("修改密码失败！");// .setCallbackType("").toString();
	}

	public String resetPsw() {
		User user = this.userManager.find(getId());
		this.userManager.restPsw(user, "123456");
		return ajaxReturn.success("重置密码成功！");
	}

	public String assignRole() {
		String uid = this.getRequest().getParameter("userId");
		String rid = this.getRequest().getParameter("roleId");
		String priority = this.getRequest().getParameter("priority");
		UserRole ur = new UserRole();
		ur.setUser(this.userManager.find(Long.parseLong(uid)));
		ur.setRole(this.roleManager.find(Long.parseLong(rid)));
		ur.setPriority(Integer.parseInt(priority));
		userRoleManager.save(ur);
		return ajaxReturn.success("添加角色成功。");
	}

	public String listUnassignRole() {
		userId = this.getId();
		userRoles = userRoleManager.findByUserId(this.getId());
		roles = roleManager.findAll();

		List<Role> rentList = Lists.newArrayList();
		// 删除已分配roles
		for (Role role : roles) {
			boolean isHas = false;
			for (UserRole or : userRoles) {
				if (or.getRole().getId().equals(role.getId())) {
					isHas = true;
					break;
				}
			}
			if (isHas == false) {
				rentList.add(role);
			}
		}
		roles = rentList;
		/*
		 * map.put("userRoles", userRoles); map.put("roles", rentList);
		 * 
		 * map.put("userId", this.getId());
		 */
		return this.SUCCESS;
	}

	public String listAssginedUserRole() {
		userId = this.getId();
		userRoles = userRoleManager.findByUserId(userId);
		return this.SUCCESS;
	}

	public String deleteUserRole() {
		String urid = this.getRequest().getParameter("userRoleId");
		Long userRoleId = Long.parseLong(urid);
		UserRole userRole = userRoleManager.find(userRoleId);
		userRoleManager.removeById(userRoleId);
		return this.SUCCESS;
	}

	private List<UserRole> userRoles;
	private List<Role> roles;
	private Long userId;

	private UserManager userManager;
	private UserRoleManager userRoleManager;
	private RoleManager roleManager;
	// @Valid
	private User model;

	/*
	 * public UserManager getUserManager() { return userManager; }
	 */

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setUserRoleManager(UserRoleManager userRoleManager) {
		this.userRoleManager = userRoleManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public User getModel() {
		return model;
	}

	public void setModel(User model) {
		this.model = model;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Long getUserId() {
		return userId;
	}

}
