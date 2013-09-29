package org.sbbs.demo.service.impl;

import org.sbbs.base.service.impl.BaseTreeManagerImpl;
import org.sbbs.demo.dao.DemoTreeNodeDao;
import org.sbbs.demo.model.DemoTreeNode;
import org.sbbs.demo.service.DemoTreeNodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("demoTreeNodeManager")
public class DemoTreeNodeManagerImpl
		extends
			BaseTreeManagerImpl<DemoTreeNode, Long>
		implements
			DemoTreeNodeManager {
	private DemoTreeNodeDao demoTreeNodeNestSetDao;

	@Autowired
	public DemoTreeNodeManagerImpl(DemoTreeNodeDao demoTreeNodeDao) {
		super(demoTreeNodeDao);
		this.demoTreeNodeNestSetDao = demoTreeNodeDao;
	}

}
