package org.sbbs.demo.dao;

import org.sbbs.base.dao.BaseTreeDao;
import org.sbbs.demo.model.DemoTreeNode;

/**
 * An interface that provides a data management interface to the DemoTreeNode
 * table.
 */
public interface DemoTreeNodeDao extends BaseTreeDao<DemoTreeNode, Long> {
	// public int getChildCount(Long pId);
}
