package org.sbbs.demo.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.sbbs.base.dao.BaseDaoTestCase;
import org.sbbs.base.dao.GeneralDao;
import org.sbbs.base.dao.hibernate.CallReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

public class GeneralDaoCallTest extends BaseDaoTestCase {
	@Autowired
	GeneralDao generalDao;

	@Test
	public void dummyTest() {
		Assert.assertTrue(true);
	}
 
	// @Test
	// @Rollback(false)
	public void testCommonCall() {
		Object[] args = { 10, 10 };

		CallReturn cr = this.generalDao.procedureCall("commonProcedure", args);
		// this.flush();
		Assert.assertTrue(cr.getReturnCode() == 0);
		Assert.assertTrue(cr.getReturnMessage().equals("20"));
	}

	// @Test
	// @Rollback(false)
	public void testQueryCall() {
		Object[] args = {};

		List list = this.generalDao.callQuery("queryProcedure", args);
		// this.flush();
		Assert.assertTrue(list.size() > 0);
		// Assert.assertTrue(cr.getReturnMessage().equals("20"));
	}

	// @Test
	public void testIHOSCall() {
		Object[] args = { "201307" };

		CallReturn cr = this.generalDao.procedureCall("sp_deptkeyCalc_1", args);

		System.out.println(cr.getReturnCode());
		System.out.println(cr.getReturnMessage());
		// Assert.assertTrue(cr.getReturnCode()==0);
		// Assert.assertTrue(cr.getReturnMessage().equals("20"));
	}

}
