package org.sbbs.security.service;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;
import org.sbbs.base.service.BaseManagerTestCase;
import org.sbbs.security.model.Permission;
import org.sbbs.security.model.Role;
import org.sbbs.security.model.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleManagerTest extends BaseManagerTestCase {
	@Autowired
	RoleManager roleManager;

	//@Test
	public void testInsertRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateRole() {
		Long id = 9L;

		Role r = this.roleManager.find(id);

		r.setRolePermissions(null);

		this.roleManager.updateRole(r);

		r = this.roleManager.find(id);

		Assert.assertNull(r.getRolePermissions());

	}

	@Test
	public void testUpdateRole1() {
		Long id = 9L;

		Role r = this.roleManager.find(id);
		Permission p = new Permission();
		p.setId(31l);
		RolePermission rp = new RolePermission();
		rp.setPermission(p);
		r.getRolePermissions().add(rp);

		this.roleManager.updateRole(r);

		r = this.roleManager.find(id);

		Assert.assertEquals(5, r.getRolePermissions().size());

	}
}
