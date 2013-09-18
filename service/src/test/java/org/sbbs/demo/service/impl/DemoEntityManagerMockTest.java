package org.sbbs.demo.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sbbs.base.service.impl.BaseManagerMockTestCase;
import org.sbbs.demo.dao.DemoEntityDao;
import org.sbbs.demo.model.DemoEntity;
@RunWith(MockitoJUnitRunner.class)
public class DemoEntityManagerMockTest extends BaseManagerMockTestCase {
	@InjectMocks
	private DemoEntityManagerImpl demoEntityManager = null;
	@Mock
	private DemoEntityDao demoEntityDao;

	@Before
	public void setUp() throws Exception {
		demoEntityManager = new DemoEntityManagerImpl(demoEntityDao);
	}

	@Test
	public void testFind() {
		// given
		final DemoEntity testData = new DemoEntity();
		testData.setDemoId(1l);
		given(demoEntityDao.find(1L)).willReturn(testData);
		// then
		DemoEntity de = demoEntityManager.find(1l);
		// then
		assertTrue(de != null);
		assert de != null;
	}

	@Test
	public void testNotFind() {
		// given
		final DemoEntity testData = null;
		given(demoEntityDao.find(1L)).willReturn(testData);
		// then
		DemoEntity de = demoEntityManager.find(1l);
		// then
		assertTrue(de == null);
		assert de == null;
	}
}
