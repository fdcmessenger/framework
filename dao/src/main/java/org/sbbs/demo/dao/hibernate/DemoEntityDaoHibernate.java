package org.sbbs.demo.dao.hibernate;

import org.sbbs.base.dao.hibernate.BaseDAOImpl;
import org.sbbs.demo.dao.DemoEntityDao;
import org.sbbs.demo.model.DemoEntity;
import org.springframework.stereotype.Repository;


@Repository("demoEntityDao")
public class DemoEntityDaoHibernate extends BaseDAOImpl<DemoEntity, Long>
		implements
			DemoEntityDao {


}
