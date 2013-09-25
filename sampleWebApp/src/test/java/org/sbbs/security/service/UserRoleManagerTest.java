package org.sbbs.security.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.sbbs.base.service.BaseManagerTestCase;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRoleManagerTest extends BaseManagerTestCase {

	@Autowired
	UserRoleManager userRoleManager;

	@Test
	public void testFindByUserId() {
		List l = this.userRoleManager.findByUserId(3L);

		Assert.assertNotNull(l);
		Assert.assertEquals(1, l.size());
		
		Assert.assertTrue(true);
	}
/*	@Autowireds
	CacheManager cacheManager;

	@After
	public void afterClass() {
		cacheManager.shutdown();
	}*/
}
