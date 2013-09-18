package org.sbbs.demo.webapp.action;

import org.apache.struts2.StrutsSpringTestCase;
import org.sbbs.demo.model.DemoEntity;

import com.opensymphony.xwork2.ActionProxy;

public class DemoDwzMaintainActionTest extends StrutsSpringTestCase {
	public void testAdd() throws Exception {
		ActionProxy proxy = getActionProxy("/addEntity");
		DemoDwzMaintainAction action = (DemoDwzMaintainAction) proxy
				.getAction();
		String result = proxy.execute();

		assertEquals("success", result);
		assertTrue(action.getEditType() == 1);// EDITTYPE_ADD
		assertTrue(action.getModel() != null);
		assertTrue(action.getModel() instanceof DemoEntity);
		assertTrue(action.getModel().getDemoId() == null);
	}

	public void testEdit() throws Exception {
		request.setParameter("id", "-10");
		ActionProxy proxy = getActionProxy("/editEntity");
		DemoDwzMaintainAction action = (DemoDwzMaintainAction) proxy
				.getAction();
		String result = proxy.execute();
		assertEquals("success", result);
		assertTrue(action.getEditType() == 0);// EDITTYPE_ADD
		assertTrue(action.getModel() != null);
		assertTrue(action.getModel() instanceof DemoEntity);
		assertTrue(action.getModel().getDemoId() != null);
		assertTrue(action.getModel().getDemoId() == Long.parseLong("-10"));
	}

	/*
	 * @Test public void testSave() { fail("Not yet implemented"); }
	 */
	public void testDelete() throws Exception {
		request.setParameter("ids", "-2,-3");
		ActionProxy proxy = getActionProxy("/deleteEntity");
		DemoDwzMaintainAction action = (DemoDwzMaintainAction) proxy
				.getAction();
		String result = proxy.execute();
		assertEquals("200", action.getAjaxReturn().getStatusCode());
	}

}
