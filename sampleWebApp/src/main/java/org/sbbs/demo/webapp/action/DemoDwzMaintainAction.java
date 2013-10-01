package org.sbbs.demo.webapp.action;

import org.sbbs.base.webapp.action.BaseMaintainAction;
import org.sbbs.demo.model.User;
import org.sbbs.demo.service.DemoEntityManager;

public class DemoDwzMaintainAction extends BaseMaintainAction<User, Long> {

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

			return this.ajaxReturn.error(getText("error.common",
					new String[] { e.getMessage() }));

		}
	}

	public String edit() {
		try {
			this.model = this.demoEntityManager.find(this.getId());
			this.setEditTypeEdit();
			return SUCCESS;
		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.common",
					new String[] { e.getMessage() }));
		}
	}

	public String save() {
		try {
			this.demoEntityManager.save(this.getModel());
			/*
			 * this.setMessage( getText( ( isNew() ) ? "demoEntity.added" :
			 * "demoEntity.updated", "no msg key found,save successed." ) );
			 * this.setStatusCode( AJAX_STATUS_SUCCESS ); this.setCallbackType(
			 * CALLBACKTYPE_CLOSECURRENT );
			 */
			return this.ajaxReturn.formSuccessRefreshGridCloseFormDialog(getText(
					(isNew()) ? "demoEntity.added" : "demoEntity.updated",
					"no msg key found,save successed."),this.getGridId());

		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.saved",
					new String[] { e.getMessage() }));
		}
	}

	public String delete() {
		try {
			String[] sIds = this.getIds().split(",");

			Long[] lIds = new Long[sIds.length];
			for (int i = 0; i < sIds.length; i++) {
				lIds[i] = Long.parseLong(sIds[i]);
			}

			this.demoEntityManager.removeByIds(lIds);

			return this.ajaxReturn.success(getText(
					(isNew()) ? "demoEntity.deleted" : "demoEntity.updated",
					"no msg key found,save successed."));

		} catch (Exception e) {
			return this.ajaxReturn.error(getText("error.deleted",
					new String[] { e.getMessage() }));
		}

	}

	private DemoEntityManager demoEntityManager;

	// @Valid
	private User model;

	public DemoEntityManager getDemoEntityManager() {
		return demoEntityManager;
	}

	public void setDemoEntityManager(DemoEntityManager demoEntityManager) {
		this.demoEntityManager = demoEntityManager;
	}

	public User getModel() {
		return model;
	}

	public void setModel(User model) {
		this.model = model;
	}

}
