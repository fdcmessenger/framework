package org.sbbs.demo.dao.hibernate;

import java.util.List;

import org.sbbs.base.dao.hibernate.BaseTreeDaoHibernate;
import org.sbbs.demo.dao.DemoTreeNodeDao;
import org.sbbs.demo.model.DemoTreeNode;
import org.springframework.stereotype.Repository;

@Repository("demoTreeNodeDao")
public class DemoTreeNodeDaoHibernate extends BaseTreeDaoHibernate<DemoTreeNode, Long> implements
		DemoTreeNodeDao {


}
