package org.sbbs.security.webapp.action;

import java.util.List;

import org.sbbs.base.webapp.action.BaseMaintainAction;
import org.sbbs.security.model.Organization;
import org.sbbs.security.model.OrganizationRole;
import org.sbbs.security.model.Role;
import org.sbbs.security.service.OrganizationManager;
import org.sbbs.security.service.OrganizationRoleManager;
import org.sbbs.security.service.RoleManager;

import com.google.common.collect.Lists;

public class OrganizationMaintainAction extends BaseMaintainAction<Organization, Long> {
	private OrganizationManager organizationManager;

	public void setOrganizationManager(OrganizationManager organizationManager) {
		this.organizationManager = organizationManager;
	}

	public String rebuildTree() {
		this.organizationManager.reBuildAllTree();
		return this.ajaxReturn.success("组织（organization）树结构重建成功.");
	}

	public String add() {
		try {
			this.model = new Organization();
			this.setEditTypeAdd();
			return SUCCESS;
		} catch (Exception e) {

			return this.ajaxReturn.error(getText("error.common", new String[] { e.getMessage() }));

		}
	}

	public String edit() {
		try {
			this.model = this.organizationManager.find(this.getId());
			this.setEditTypeEdit();
			return SUCCESS;
		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.common", new String[] { e.getMessage() }));
		}
	}

	public String save() {
		try {
			/*
			 * if (this.getModel().getId() == null)
			 * this.organizationManager.insertOrganization(getModel()); else
			 * this.organizationManager.updateOrganization(getModel());//
			 * save(this.getModel());
			 */
			this.organizationManager.save(getModel());
			return this.ajaxReturn.formSuccessRefreshGridCloseFormDialog(
					getText((isNew()) ? "organization.added" : "organization.updated",
							"no msg key found,save successed."), this.getGridId());

		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.saved", new String[] { e.getMessage() }));
		}
	}

	/*
	 * public String delete() { try {
	 * 
	 * this.organizationManager.removeById(this.getId()); return
	 * this.ajaxReturn.success(getText((isNew()) ? "organization.deleted" :
	 * "organization.updated", "no msg key found,save successed."));
	 * 
	 * } catch (Exception e) { return
	 * this.ajaxReturn.error(getText("error.deleted", new String[] {
	 * e.getMessage() })); } }
	 */

	public String deletes() {
		try {
			String[] sIds = this.getIds().split(",");

			Long[] lIds = new Long[sIds.length];
			for (int i = 0; i < sIds.length; i++) {
				lIds[i] = Long.parseLong(sIds[i]);
			}

			this.organizationManager.removeByIds(lIds);

			return this.ajaxReturn.success(getText((isNew()) ? "organization.deleted" : "organization.updated",
					"no msg key found,save successed."));

		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.deleted", new String[] { e.getMessage() }));
		}

	}

	public String assignRole() {
		String uid = this.getRequest().getParameter("organizationId");
		String rid = this.getRequest().getParameter("roleId");
		String priority = this.getRequest().getParameter("priority");
		OrganizationRole ur = new OrganizationRole();
		ur.setOrganization(this.organizationManager.find(Long.parseLong(uid)));
		ur.setRole(this.roleManager.find(Long.parseLong(rid)));
		ur.setPriority(Integer.parseInt(priority));
		organizationRoleManager.save(ur);
		return ajaxReturn.success("添加角色成功。");
	}

	public String listUnassignRole() {
		organizationId = this.getId();
		organizationRoles = organizationRoleManager.findByOrgId(this.getId());
		roles = roleManager.findAll();

		List<Role> rentList = Lists.newArrayList();
		// 删除已分配roles
		for (Role role : roles) {
			boolean isHas = false;
			for (OrganizationRole or : organizationRoles) {
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
		 * map.put("organizationRoles", organizationRoles); map.put("roles",
		 * rentList);
		 * 
		 * map.put("organizationId", this.getId());
		 */
		return this.SUCCESS;
	}

	public String listAssginedOrganizationRole() {
		organizationId = this.getId();
		organizationRoles = organizationRoleManager.findByOrgId(organizationId);// OrganizationId(organizationId);
		return this.SUCCESS;
	}

	public String deleteOrganizationRole() {
		String urid = this.getRequest().getParameter("organizationRoleId");
		Long organizationRoleId = Long.parseLong(urid);
		OrganizationRole organizationRole = organizationRoleManager.find(organizationRoleId);
		organizationRoleManager.removeById(organizationRoleId);
		return this.SUCCESS;
	}

	
	
	
	
	
	
	private List<OrganizationRole> organizationRoles;
	private List<Role> roles;
	private Long organizationId;

	// private OrganizationManager organizationManager;
	private OrganizationRoleManager organizationRoleManager;
	private RoleManager roleManager;
	// @Valid
	private Organization model;

	/*
	 * public OrganizationManager getOrganizationManager() { return
	 * organizationManager; }
	 */

	/*
	 * public void setOrganizationManager(OrganizationManager
	 * organizationManager) { this.organizationManager = organizationManager; }
	 */

	public void setOrganizationRoleManager(OrganizationRoleManager organizationRoleManager) {
		this.organizationRoleManager = organizationRoleManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public Organization getModel() {
		return model;
	}

	public void setModel(Organization model) {
		this.model = model;
	}

	public List<OrganizationRole> getOrganizationRoles() {
		return organizationRoles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

}
