package org.sbbs.demo.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sbbs.base.service.BaseManagerTestCase;
import org.sbbs.demo.model.DemoTreeNode;
import org.sbbs.demo.service.DemoTreeNodeManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试数据结构： 1 2 3 4 5
 * 
 * 
 */
public class DemoTreeNodeManagerImplTest extends BaseManagerTestCase {
	@Autowired
	private DemoTreeNodeManager manager;

	@Before
	public final void init() {
		this.manager.reBuildTree(1L, 1);
		this.manager.debugDisplayTree(1L);
	}

	@Test
	public final void testDummy() throws Exception {
		log.info("here is a dummy test.");
		Assert.assertTrue(true);
	}

	@Test
	public final void testBuildNestTreeFromAdjacency() {
		manager.reBuildAllTree();
		Assert.assertTrue(true);
	}

	@Test
	public final void testInsertNode() {
		long rid = 1L;
		DemoTreeNode root = (DemoTreeNode) this.manager.get(rid);

		DemoTreeNode nnode = new DemoTreeNode();
		nnode.setNodeName("testNew");
		nnode.setParentNode(root);
		// this.manager.increaseleftAndRight(root.getLft());
		nnode = (DemoTreeNode) this.manager.insertNode(nnode);
		List<DemoTreeNode> tl = this.manager.getAll();
		Assert.assertNotNull(tl);
		Assert.assertTrue(tl.size() == 6);
		this.manager.debugDisplayTree(1L);
		Assert.assertTrue(this.manager.get(1L).getLft() == 1);
		Assert.assertTrue(this.manager.get(1L).getRgt() == 12);
		Assert.assertTrue(this.manager.get(2L).getLft() == 4);
		Assert.assertTrue(this.manager.get(2L).getRgt() == 11);
		Assert.assertTrue(this.manager.get(3L).getLft() == 5);
		Assert.assertTrue(this.manager.get(3L).getRgt() == 6);
		Assert.assertTrue(this.manager.get(4L).getLft() == 7);
		Assert.assertTrue(this.manager.get(4L).getRgt() == 8);
		Assert.assertTrue(this.manager.get(5L).getLft() == 9);
		Assert.assertTrue(this.manager.get(5L).getRgt() == 10);

		Assert.assertTrue(nnode.getLft() == 2);
		Assert.assertTrue(nnode.getRgt() == 3);
	}
	
	//@Test
	public void testEnabledFilter(){
	    DemoTreeNode n = (DemoTreeNode)this.manager.get( 5l );
        n.setDisabled( true );
        this.manager.save( n );
       List tl = this.manager.getTreeByNodeId(1l);
        Assert.assertNotNull(tl);
        Assert.assertTrue(tl.size() == 4);
        
        n = (DemoTreeNode) this.manager.get( 5l );
        Assert.assertNotNull( n );
        
        List all = this.manager.getAll();
         Assert.assertNotNull(all);
            Assert.assertTrue(all.size() == 5);
	}
}
