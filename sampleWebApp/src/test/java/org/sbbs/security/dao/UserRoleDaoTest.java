package org.sbbs.security.dao;

import java.util.List;

import junit.framework.Assert;
import net.sf.ehcache.CacheManager;

import org.junit.After;
import org.junit.Test;
import org.sbbs.base.dao.BaseDaoTestCase;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRoleDaoTest extends BaseDaoTestCase {
	@Autowired
	UserRoleDao userRoleDao;

	@Test
	public void testFindByUserId() {

		List l = this.userRoleDao.findByUserId(3L);

		Assert.assertNotNull(l);
		Assert.assertEquals(1, l.size());
	}

/*	@Autowired
	CacheManager cacheManager;

	@After
	public void afterClass() {
		cacheManager.shutdown();
	}*/
}
