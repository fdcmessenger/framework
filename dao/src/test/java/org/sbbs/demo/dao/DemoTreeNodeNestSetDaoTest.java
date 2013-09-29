package org.sbbs.demo.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sbbs.base.dao.BaseDaoTestCase;
import org.sbbs.base.dao.BaseTreeDao;
import org.sbbs.demo.model.DemoTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * 测试数据结构： 1 2 3 4 5
 *
 *
 */
public class DemoTreeNodeNestSetDaoTest extends BaseDaoTestCase {
	/**
	 *
	 */
	@Autowired
	private BaseTreeDao demoTreeNodeDao;
	/**
	 *
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 *
	 */
	public final void test() {
		fail("Not yet implemented");
	}

	/**
	 *
	 */
	@Before
	@Rollback(false)
	public final void init() {
		this.demoTreeNodeDao.reBuildTree(1l, 1);
		this.demoTreeNodeDao.debugDisplayTree(1l);
	}

	/**
	 *
	 */
	/**
	 * 
	 */
	@Test
	public final void testRebuildTree() {
		this.demoTreeNodeDao.reBuildTree(1l, 1);

		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getLft() == 1);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getRgt() == 10);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getLft() == 2);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getRgt() == 9);
		Assert.assertTrue(this.demoTreeNodeDao.get(3l).getLft() == 3);
		Assert.assertTrue(this.demoTreeNodeDao.get(3l).getRgt() == 4);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getLft() == 5);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getRgt() == 6);
		Assert.assertTrue(this.demoTreeNodeDao.get(5l).getLft() == 7);
		Assert.assertTrue(this.demoTreeNodeDao.get(5l).getRgt() == 8);

		this.demoTreeNodeDao.debugDisplayTree(1l);
	}

	/**
	 *
	 */
	@Test
	public void testGet() {
		DemoTreeNode node = (DemoTreeNode) this.demoTreeNodeDao.get(1l);
		Assert.assertNotNull(node);
	}

	/**
	 *
	 */
	@Test
	public void testGetAll() {
		List l = this.demoTreeNodeDao.getAll();
		Assert.assertTrue(l.size() == 5);
	}

	/**
	 *
	 */
	@Test
	public void testGetAllRoots() {
		List l = this.demoTreeNodeDao.getAllRoots();
		Assert.assertTrue(l.size() == 1);
	}

	/**
	 *
	 */
	@Test
	public void testGetImmediateDescendant() {
		List l = this.demoTreeNodeDao.getImmediateDescendant(1l);
		Assert.assertTrue(l.size() == 1);
	}

	/**
	 *
	 */
	@Test
	public void testGetImmediateLeafDescendant() {
		List l = this.demoTreeNodeDao.getImmediateLeafDescendant(1l);
		Assert.assertTrue(l.size() == 0);
		l = this.demoTreeNodeDao.getImmediateLeafDescendant(2l);
		Assert.assertTrue(l.size() == 3);
	}

	/**
	 * @author Administrator
	 *
	 */
	@Test
	public void testGetAllNodeAtLevel() {
		List l = this.demoTreeNodeDao.getAllNodeAtLevel(2);
		Assert.assertTrue(l.size() == 3);
	}

	/**
	 *
	 */
	@Test
	public void testGetAllNodeAtLevelUnderNode() {
		List l = this.demoTreeNodeDao.getAllNodeAtLevelUnderNode(1l, 2);
		Assert.assertTrue(l.size() == 3);
	}

	@Test
	public void testGetAllLeafNodeAtLevelUnderNode() {
		List l = this.demoTreeNodeDao.getAllLeafNodeAtLevelUnderNode(1l,
				2);
		Assert.assertTrue(l.size() == 3);
	}

	@Test
	public void testGetAllUnLeafNodeAtLevelUnderNode() {
		List l = this.demoTreeNodeDao.getAllUnLeafNodeAtLevelUnderNode(
				1l, 2);
		Assert.assertTrue(l.size() == 0);
	}

	@Test
	public void testGetImmediateUnLeafDescendant() {
		List l = this.demoTreeNodeDao.getImmediateUnLeafDescendant(1l);
		Assert.assertTrue(l.size() == 1);
		l = this.demoTreeNodeDao.getImmediateUnLeafDescendant(2l);
		Assert.assertTrue(l.size() == 0);
	}

	@Test
	public void testGetTree() {
		List tl = this.demoTreeNodeDao.getTreeByNodeId(1l);
		Assert.assertNotNull(tl);
		Assert.assertTrue(tl.size() == 5);
		tl = this.demoTreeNodeDao.getTreeByNodeId(2l);
		Assert.assertTrue(tl.size() == 4);
		tl = this.demoTreeNodeDao.getTreeByNodeId(3l);
		Assert.assertTrue(tl.size() == 1);
		tl = this.demoTreeNodeDao.getTreeByNodeId(4l);
		Assert.assertTrue(tl.size() == 1);
		tl = this.demoTreeNodeDao.getTreeByNodeId(5l);
		Assert.assertTrue(tl.size() == 1);
		
		
		DemoTreeNode n = (DemoTreeNode)this.demoTreeNodeDao.get( 5l );
		n.setDisabled( true );
		this.demoTreeNodeDao.save( n );
		tl = this.demoTreeNodeDao.getTreeByNodeId(1l);
        Assert.assertNotNull(tl);
        Assert.assertTrue(tl.size() == 5);
        
        n = (DemoTreeNode) this.demoTreeNodeDao.get( 5l );
		Assert.assertNotNull( n );
		
		List all = this.demoTreeNodeDao.getAll();
		 Assert.assertNotNull(all);
	        Assert.assertTrue(all.size() == 5);
		
	}

	@Test
	public void testGetPathToNode() {
		List pl = this.demoTreeNodeDao.getPathToNode(5l, true);
		Assert.assertNotNull(pl);
		Assert.assertTrue(pl.size() == 3);
		Assert.assertEquals(1l, ((DemoTreeNode) pl.get(0)).getId().longValue());
		Assert.assertEquals(2l, ((DemoTreeNode) pl.get(1)).getId().longValue());
		Assert.assertEquals(5l, ((DemoTreeNode) pl.get(2)).getId().longValue());

		pl = this.demoTreeNodeDao.getPathToNode(4l, false);
		Assert.assertNotNull(pl);
		Assert.assertTrue(pl.size() == 2);
		Assert.assertEquals(1l, ((DemoTreeNode) pl.get(0)).getId().longValue());
		Assert.assertEquals(2l, ((DemoTreeNode) pl.get(1)).getId().longValue());
		// Assert.assertEquals(10l,
		// ((DemoTreeNode)pl.get(2)).getNodeId().longValue());

	}

	@Test
	public void testInsertNode() {
		long rid = 1l;
		DemoTreeNode root = (DemoTreeNode) this.demoTreeNodeDao.get(rid);

		DemoTreeNode nnode = new DemoTreeNode();
		nnode.setNodeName("testNew");
		nnode.setParentNode(root);
		// this.demoTreeNodeNestSetDao.increaseleftAndRight(root.getLft());
		nnode = (DemoTreeNode) this.demoTreeNodeDao.insertNode(nnode);
		List tl = this.demoTreeNodeDao.getAll();
		Assert.assertNotNull(tl);
		Assert.assertTrue(tl.size() == 6);
		this.demoTreeNodeDao.debugDisplayTree(1l);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getLft() == 1);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getRgt() == 12);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getLft() == 4);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getRgt() == 11);
		Assert.assertTrue(this.demoTreeNodeDao.get(3l).getLft() == 5);
		Assert.assertTrue(this.demoTreeNodeDao.get(3l).getRgt() == 6);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getLft() == 7);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getRgt() == 8);
		Assert.assertTrue(this.demoTreeNodeDao.get(5l).getLft() == 9);
		Assert.assertTrue(this.demoTreeNodeDao.get(5l).getRgt() == 10);

		Assert.assertTrue(nnode.getLft() == 2);
		Assert.assertTrue(nnode.getRgt() == 3);
	}

	@Test
	public void testGetAllLeafChildren() {
		List ll = this.demoTreeNodeDao.getAllLeafDescendant(1l);
		Assert.assertNotNull(ll);
		Assert.assertTrue(ll.size() == 3);
	}

	@Test
	public void testGetAllChildren() {
		List tl = this.demoTreeNodeDao.getAllDescendant(1l);
		Assert.assertNotNull(tl);
		Assert.assertTrue(tl.size() == 4);
		tl = this.demoTreeNodeDao.getAllDescendant(2l);
		Assert.assertTrue(tl.size() == 3);
		tl = this.demoTreeNodeDao.getAllDescendant(3l);
		Assert.assertTrue(tl.size() == 0);

	}

	@Test
	public final void testDelete0() {
		this.demoTreeNodeDao.delete(5l);
		List tl = this.demoTreeNodeDao.getAll();
		this.demoTreeNodeDao.debugDisplayTree(1l);
		Assert.assertTrue(tl.size() == 4);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getLft() == 1);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getRgt() == 8);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getLft() == 2);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getRgt() == 7);
		Assert.assertTrue(this.demoTreeNodeDao.get(3l).getLft() == 3);
		Assert.assertTrue(this.demoTreeNodeDao.get(3l).getRgt() == 4);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getLft() == 5);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getRgt() == 6);
	}

	@Test
	public void testDelete1() {
		/*
		 * this.demoTreeNodeNestSetDao.delete(6l); List tl =
		 * this.demoTreeNodeNestSetDao.getAll();
		 * Assert.assertTrue(tl.size()==5);
		 */
		this.demoTreeNodeDao.delete(2l);
		List tl = this.demoTreeNodeDao.getAll();
		Assert.assertTrue(tl.size() == 1);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getLft() == 1);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getRgt() == 2);
		/*
		 * this.demoTreeNodeNestSetDao.delete(1l); tl =
		 * this.demoTreeNodeNestSetDao.getAll();
		 * Assert.assertTrue(tl.size()==0);
		 */
	}

	@Test
	public void testDelete2() {
		this.demoTreeNodeDao.delete(5l);
		List tl = this.demoTreeNodeDao.getAll();
		this.demoTreeNodeDao.debugDisplayTree(1l);
		Assert.assertTrue(tl.size() == 4);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getLft() == 1);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getRgt() == 8);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getLft() == 2);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getRgt() == 7);
		Assert.assertTrue(this.demoTreeNodeDao.get(3l).getLft() == 3);
		Assert.assertTrue(this.demoTreeNodeDao.get(3l).getRgt() == 4);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getLft() == 5);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getRgt() == 6);

		this.demoTreeNodeDao.delete(3l);
		tl = this.demoTreeNodeDao.getAll();
		this.demoTreeNodeDao.debugDisplayTree(1l);
		Assert.assertTrue(tl.size() == 3);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getLft() == 1);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getRgt() == 6);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getLft() == 2);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getRgt() == 5);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getLft() == 3);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getRgt() == 4);

	}

	@Test
	public void testDebugDisplayTree() {
		 this.demoTreeNodeDao.debugDisplayTree(1l);
		Assert.assertTrue(true);
	}

	@Test
	public void testMove() {
		this.demoTreeNodeDao.debugDisplayTree(1l);
		this.demoTreeNodeDao.move(5l, 4l);
		this.demoTreeNodeDao.debugDisplayTree(1l);
		List l = this.demoTreeNodeDao.getAll();
		Assert.assertTrue(l.size() == 5);

		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getLft() == 1);
		Assert.assertTrue(this.demoTreeNodeDao.get(1l).getRgt() == 10);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getLft() == 2);
		Assert.assertTrue(this.demoTreeNodeDao.get(2l).getRgt() == 9);
		Assert.assertTrue(this.demoTreeNodeDao.get(3l).getLft() == 3);
		Assert.assertTrue(this.demoTreeNodeDao.get(3l).getRgt() == 4);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getLft() == 5);
		Assert.assertTrue(this.demoTreeNodeDao.get(4l).getRgt() == 8);
		Assert.assertTrue(this.demoTreeNodeDao.get(5l).getLft() == 6);
		Assert.assertTrue(this.demoTreeNodeDao.get(5l).getRgt() == 7);
		/*
		 * this.demoTreeNodeNestSetDao.move(2l, 7l);
		 * this.demoTreeNodeNestSetDao.debugDisplayTree(1l); l =
		 * this.demoTreeNodeNestSetDao.getAll();
		 * Assert.assertTrue(l.size()==10);
		 */

	}
	/*
	 * @Test public void testIncrease(){
	 * this.demoTreeNodeNestSetDao.increaseleftAndRight(1);
	 * this.demoTreeNodeNestSetDao.debugDisplayTree(1l);
	 * Assert.assertTrue(this.demoTreeNodeNestSetDao.get(1l).getLft()==1);
	 * Assert.assertTrue(this.demoTreeNodeNestSetDao.get(1l).getRgt()==12);
	 * Assert.assertTrue(this.demoTreeNodeNestSetDao.get(2l).getLft()==4);
	 * Assert.assertTrue(this.demoTreeNodeNestSetDao.get(2l).getRgt()==11);
	 * Assert.assertTrue(this.demoTreeNodeNestSetDao.get(3l).getLft()==5);
	 * Assert.assertTrue(this.demoTreeNodeNestSetDao.get(3l).getRgt()==6);
	 * Assert.assertTrue(this.demoTreeNodeNestSetDao.get(4l).getLft()==7);
	 * Assert.assertTrue(this.demoTreeNodeNestSetDao.get(4l).getRgt()==8);
	 * Assert.assertTrue(this.demoTreeNodeNestSetDao.get(5l).getLft()==9);
	 * Assert.assertTrue(this.demoTreeNodeNestSetDao.get(5l).getRgt()==10);
	 * }
	 */
}
