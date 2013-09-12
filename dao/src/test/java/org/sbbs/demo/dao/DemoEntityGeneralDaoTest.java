package org.sbbs.demo.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.proxy.HibernateProxy;
import org.junit.Assert;
import org.junit.Test;
import org.sbbs.base.dao.BaseDaoTestCase;
import org.sbbs.base.dao.GeneralDao;
import org.sbbs.base.dao.hibernate.CallReturn;
import org.sbbs.demo.model.DemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Sort;

public class DemoEntityGeneralDaoTest extends BaseDaoTestCase {
	@Autowired
	GeneralDao generalDao;
	@Test
	public void testCommonProcedure(){
		Object[] args = {10,10};
		CallReturn cr = this.generalDao.procedureCall("commonProcedure", args);
		
		Assert.assertTrue(cr.getReturnCode()==0);
		Assert.assertTrue(cr.getReturnMessage().equals("20"));
		
	}

	@Test
	public void testFind() {
		Long id = -1l;
		DemoEntity de = this.generalDao.find(DemoEntity.class, id);
		Assert.assertNotNull(de);
		de = this.generalDao.find(DemoEntity.class, 1l);
		Assert.assertNull(de);

	}

	@Test
	public void testFindByIds() {
		Long[] ids = { -1l, -7l, -200l };
		DemoEntity[] de = this.generalDao.find(DemoEntity.class, ids);
		Assert.assertTrue(de.length == 3);
		Long[] ids1 = { 1l, 2l };
		de = this.generalDao.find(DemoEntity.class, ids1);
		Assert.assertTrue(de.length == 2);
		Assert.assertNull(de[0]);
		Assert.assertNull(de[1]);

		Long[] ids2 = { -1l, 2l };
		de = this.generalDao.find(DemoEntity.class, ids2);
		Assert.assertTrue(de.length == 2);
		Assert.assertNotNull(de[0]);
		Assert.assertNull(de[1]);
	}

	@Test
	public void testGetReference() {
		Long id1 = -1l;// , id2 = 1l;
		DemoEntity de0 = this.generalDao.find(DemoEntity.class, -2l);
		DemoEntity de = this.generalDao.getReference(DemoEntity.class, id1);
		Assert.assertNotNull(de);
		Assert.assertTrue(de instanceof HibernateProxy);
		Assert.assertTrue(!(de0 instanceof HibernateProxy));
	}

	@Test(expected = HibernateException.class)
	public void testGetReferenceException() throws Exception {
		DemoEntity de = this.generalDao.getReference(DemoEntity.class, 1l);
		Assert.assertNotNull(de);
		try {
			int intv = de.getIntField();
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			throw e;

		}
	}

	@Test
	public void testGetReferences() {
		Long[] ids0 = { -1l, -2l };// , ids1 = { 1l, 2l };
		DemoEntity[] des = this.generalDao
				.getReferences(DemoEntity.class, ids0);
		Assert.assertTrue(des.length == 2);
		Assert.assertTrue(des[0] instanceof HibernateProxy);
		int iv = des[0].getIntField();
	}

	/**
     *
     */
	@Test(expected = HibernateException.class)
	public void testGetReferencesException() {
		Long[] ids0 = { 1l, 2l };
		DemoEntity[] des = this.generalDao
				.getReferences(DemoEntity.class, ids0);
		Assert.assertTrue(des.length == 2);
		Assert.assertTrue(des[0] instanceof HibernateProxy);
		int iv = des[0].getIntField();

	}

	@Test
	public void testSave_Update() {
		DemoEntity de = this.generalDao.find(DemoEntity.class, -1l);
		de.setIntField(100);
		boolean saveType = this.generalDao.save(de);
		Assert.assertTrue(!saveType);
		DemoEntity de1 = this.generalDao.find(DemoEntity.class, -1l);
		Assert.assertEquals(100, de1.getIntField());
		this.generalDao.flush();
		int jdbcv = this.jdbcTemplate
				.queryForInt("SELECT a.intField from t_demo_entity a where demoId=-1 ");
		Assert.assertTrue(jdbcv == de1.getIntField());
	}

	@Test
	public void testSave_insert() {
		DemoEntity de = new DemoEntity();
		de.setIntField(99);
		de.setStringField("aaa");
		boolean saveType = this.generalDao.save(de);
		Assert.assertTrue(saveType);
		Assert.assertNotNull(de.getDemoId());
		// this.demoEntityDao.flush();
		int jdbcv = this.jdbcTemplate
				.queryForInt("SELECT a.intField from t_demo_entity a where demoId="
						+ de.getDemoId());
		Assert.assertTrue(jdbcv == de.getIntField());
		List l = this.generalDao.findAll(DemoEntity.class);
		Assert.assertTrue(l.size() == 201);
	}

	@Test
	public void testRemove() {
		Long id = -1l;
		boolean rs = this.generalDao.removeById(DemoEntity.class, id);
		List l = this.generalDao.findAll(DemoEntity.class);
		Assert.assertTrue(l.size() == 199);
		Assert.assertTrue(rs);
		Long id1 = 1l;
		boolean rs1 = this.generalDao.removeById(DemoEntity.class, id1);
		List l1 = this.generalDao.findAll(DemoEntity.class);
		Assert.assertTrue(l1.size() == 199);
		Assert.assertTrue(!rs1);
	}

	@Test
	public void testRemove1() {
		Long id = -1l;
		DemoEntity de = this.generalDao.find(DemoEntity.class, id);
		boolean rs = this.generalDao.remove(de);
		List l = this.generalDao.findAll(DemoEntity.class);
		Assert.assertTrue(l.size() == 199);
		Assert.assertTrue(rs);

		rs = this.generalDao.remove(de);
		l = this.generalDao.findAll(DemoEntity.class);
		Assert.assertTrue(l.size() == 199);
		Assert.assertTrue(!rs);
	}

	@Test
	public void testRemoveByIds() {
		Long[] ids1 = { -1l, -2l, -3l }, ids2 = { -4l, 1l };
		this.generalDao.removeByIds(DemoEntity.class, ids1);
		List l = this.generalDao.findAll(DemoEntity.class);
		Assert.assertTrue(l.size() == 197);
		this.generalDao.removeByIds(DemoEntity.class, ids2);
		l = this.generalDao.findAll(DemoEntity.class);
		Assert.assertTrue(l.size() == 196);
	}

	@Test
	public void testRemoveEntities() {
		List l = this.generalDao.getHibernateTemplate().find(
				"from DemoEntity where demoId>=-50 and demoId<=-1");
		Assert.assertTrue(l.size() == 50);
		DemoEntity[] des = new DemoEntity[l.size()];
		l.toArray(des);
		this.generalDao.remove(des);

		List all = this.generalDao.findAll(DemoEntity.class);
		Assert.assertTrue(all.size() == 150);

	}

	@Test
	public void testFindAll() {
		List l = this.generalDao.findAll(DemoEntity.class);
		Assert.assertTrue(l.size() == 200);

	}

	@Test
	public void testSearch() {
		Search s = new Search(DemoEntity.class);
		s.addFilterLessThan("demoId", -1l);
		s.addFilterGreaterThan("demoId", -50l);
		List l = this.generalDao.search(s);
		Assert.assertTrue(l.size() == 48);
	}

	@Test
	public void testCount() {
		Search s = new Search(DemoEntity.class);
		s.addFilterLessThan("demoId", -1l);
		s.addFilterGreaterThan("demoId", -50l);
		int c = this.generalDao.count(s);
		Assert.assertTrue(c == 48);
	}

	@Test
	public void testSearchUnique() {
		Search s = new Search(DemoEntity.class);
		s.addFilterLessThan("demoId", -1l);
		s.addFilterGreaterThan("demoId", -3l);
		Object o = this.generalDao.searchUnique(s);
		Assert.assertNotNull(o);
	}

	@Test
	public void testSearchUniqueNull() {
		Search s = new Search(DemoEntity.class);
		s.addFilterLessThan("demoId", -1l);
		s.addFilterGreaterThan("demoId", -2l);
		Object o = this.generalDao.searchUnique(s);
		Assert.assertNull(o);
	}

	@Test(expected = IncorrectResultSizeDataAccessException.class)
	public void testSearchUniqueException() {
		Search s = new Search(DemoEntity.class);
		s.addFilterLessThan("demoId", -1l);
		s.addFilterGreaterThan("demoId", -5l);
		Object o = this.generalDao.searchUnique(s);
		Assert.assertNotNull(o);
	}

	@Test
	public void testSearchSort() {
		Search s = new Search(DemoEntity.class);
		s.addFilterLessThan("demoId", -1l);
		s.addFilterGreaterThan("demoId", -50l);
		s.addSort("demoId", true);
		List l = this.generalDao.search(s);
		Assert.assertTrue(l.size() == 48);
		Assert.assertTrue(((DemoEntity) l.get(0)).getDemoId() == -2l);

		s.removeSort("demoId");
		s.addSort("demoId", false);
		l = this.generalDao.search(s);

		Assert.assertTrue(l.size() == 48);
		Assert.assertTrue(((DemoEntity) l.get(0)).getDemoId() == -49l);
	}

	@Test
	/**
	 * TODO 还没有搞明白具体的使用方式
	 */
	public void testSearchResultMode() {
		Search s = new Search(DemoEntity.class);
		s.addFilterLessThan("demoId", -1l);
		s.addFilterGreaterThan("demoId", -50l);
		// s.setResultMode( Search.RESULT_ARRAY );
		// List l = this.demoEntityDao.search( s );
		// s.setResultMode( Search.RESULT_LIST );
		// List l1 = this.demoEntityDao.search( s );
		s.addField("demoId");
		s.setResultMode(Search.RESULT_MAP);
		List l2 = this.generalDao.search(s);
		// s.setResultMode( Search.RESULT_SINGLE );
		// List l3 = this.demoEntityDao.search( s );

		Assert.assertTrue(l2.size() == 48);
		// Assert.assertTrue(((DemoEntity) l.get( 0 )).getDemoId()==-2l );

	}

	@Test
	public void testSearchPaging() {
		Search s = new Search(DemoEntity.class);
		s.addFilterLessOrEqual("demoId", -1l);
		s.addFilterGreaterOrEqual("demoId", -50l);
		s.addSort(Sort.desc("demoId"));
		List l = this.generalDao.search(s);
		Assert.assertTrue(l.size() == 50);
		s.setMaxResults(3);
		l = this.generalDao.search(s);
		Assert.assertTrue(l.size() == 3);
		s.setFirstResult(4);
		l = this.generalDao.search(s);
		Assert.assertTrue(l.size() == 3);
		Assert.assertTrue(((DemoEntity) l.get(0)).getDemoId() == -5);

		s.setMaxResults(-1);
		l = this.generalDao.search(s);
		Assert.assertTrue(((DemoEntity) l.get(0)).getDemoId() == -5);
		Assert.assertTrue(l.size() == 46);

		s.setMaxResults(4);
		s.setPage(1);
		s.setFirstResult(2);
		l = this.generalDao.search(s);

		s.setPage(0);
		s.setFirstResult(-1);
		l = this.generalDao.search(s);
		Assert.assertTrue(l.size() == 4);
	}

	@Test
	public void testSearchPaging_1() {
		Search s = new Search(DemoEntity.class);
		s.addFilterLessOrEqual("demoId", -1l);
		s.addFilterGreaterOrEqual("demoId", -50l);
		s.addSort(Sort.desc("demoId"));
		s.setMaxResults(4);
		s.setPage(0);
		List l = this.generalDao.search(s);
		Assert.assertTrue(l.size() == 4);
		Assert.assertTrue(((DemoEntity) l.get(0)).getDemoId() == -1);
		s.setPage(2);
		l = this.generalDao.search(s);
		Assert.assertTrue(l.size() == 4);
		Assert.assertTrue(((DemoEntity) l.get(0)).getDemoId() == -9);

		s.setPage(10);
		l = this.generalDao.search(s);
		Assert.assertTrue(l.size() == 4);
		Assert.assertTrue(((DemoEntity) l.get(0)).getDemoId() == -41);
	}

	@Test
	public void testFields() {
		List<Object[]> resultArray;
		List<List<?>> resultList;
		List<Map<String, Object>> resultMap;

		Search s = new Search(DemoEntity.class);
		s.addFilterLessOrEqual("demoId", -1l);
		s.addFilterGreaterOrEqual("demoId", -50l);
		s.addSort(Sort.desc("demoId"));
		s.addField("intField");
		s.addField("demoId");

		s.setResultMode(Search.RESULT_ARRAY);
		resultArray = this.generalDao.search(s);
		Assert.assertTrue(resultArray.size() == 50);

		s.setResultMode(Search.RESULT_LIST);
		resultList = this.generalDao.search(s);
		Assert.assertTrue(resultList.size() == 50);

		s.setResultMode(Search.RESULT_MAP);
		resultMap = this.generalDao.search(s);
		Assert.assertTrue(resultMap.size() == 50);

		s.clearFields();
		s.addField(Field.ROOT_ENTITY);
		resultMap = this.generalDao.search(s);
		Assert.assertTrue(resultMap.size() == 50);

	}

	@Test
	public void testSearchAndCount() {
		Search s = new Search(DemoEntity.class);
		s.addFilterLessOrEqual("demoId", -1l);
		s.addFilterGreaterOrEqual("demoId", -50l);
		s.addSort(Sort.desc("demoId"));

		s.setPage(0);
		s.setMaxResults(20);
		SearchResult sr = this.generalDao.searchAndCount(s);
		Assert.assertTrue(sr.getTotalCount() == 50);
		Assert.assertTrue(sr.getResult().size() == 20);
		Assert.assertTrue(((DemoEntity) sr.getResult().get(0)).getDemoId() == -1l);

	}

	@Test
	public void testExists() {
		boolean rs = this.generalDao.exists(DemoEntity.class, -1l);
		Assert.assertTrue(rs);
		rs = this.generalDao.exists(DemoEntity.class, 1l);
		Assert.assertTrue(!rs);
	}

	@Test
	public void testGetHibernateTemplate() {
		Assert.assertNotNull(this.generalDao.getHibernateTemplate());
	}

	@Test
	public void testGetJdbcTemplate() {
		Assert.assertNotNull(this.generalDao.getJdbcTemplate());
	}
}
