package org.sbbs.demo.webapp.action;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.StrutsSpringTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

public class DemoDwzPagedActionTest extends StrutsSpringTestCase {
	
	
	@Override
	protected String[] getContextLocations() {
		return new String[] {"classpath*:applicationContext.xml"};
	}

	@Test
	public void testGridPageList() throws Exception {
		ActionProxy proxy = getActionProxy("/demoEntityList");
		DemoDwzPagedAction action = (DemoDwzPagedAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals("success", result);
		ActionMapping mapping = getActionMapping("/demoEntityList");
		assertNotNull(mapping);
		assertEquals("/", mapping.getNamespace());
		assertEquals("demoEntityList", mapping.getName());
		action.printAllStruts2Actions();

	}

	@Test
	public void testDemoEntityGrid() throws Exception {
		ActionProxy proxy = getActionProxy("/demoEntityGrid");
		DemoDwzPagedAction action = (DemoDwzPagedAction) proxy.getAction();
		String result = proxy.execute();
		assertTrue(action.getPageList().size() == 20);
		assertTrue(action.getTotal() == 10);
		assertTrue(action.getPage() == 1);
		// JSONObject jsonObject = JSONObject.fromObject(action.getPageList());
		 JSONArray jsonArray = JSONArray.fromObject( action.getPageList() );  
	        System.out.println( jsonArray );  

	}
	@Test
	public void testDemoEntityGridJson() throws Exception {
		ActionProxy proxy = getActionProxy("/demoEntityGrid");
		DemoDwzPagedAction action = (DemoDwzPagedAction) proxy.getAction();
		String result = proxy.execute();
		assertTrue(action.getPageList().size() == 20);
		assertTrue(action.getTotal() == 10);
		assertTrue(action.getPage() == 1);
		
		JsonConfig config = new JsonConfig();  
		String[] excludeProperties = new String[]{  
		"timestampField" 
		};  
		config.setExcludes(excludeProperties);  
		//JSONObject jsonObject = JSONObject.fromObject(obj,config);  
		JSONArray jsonArray = JSONArray.fromObject( action.getPageList(), config);  
		String jsonStr = jsonArray.toString();  
		System.out.println( jsonStr );  
		
	}

}
