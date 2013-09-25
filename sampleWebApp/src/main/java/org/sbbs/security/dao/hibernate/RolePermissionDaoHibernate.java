package org.sbbs.security.dao.hibernate;

import java.util.List;

import org.sbbs.base.dao.hibernate.BaseDAOImpl;
import org.sbbs.security.dao.RolePermissionDao;
import org.sbbs.security.model.RolePermission;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;

@Repository("rolePermissionDao")
public class RolePermissionDaoHibernate extends BaseDAOImpl<RolePermission, Long> implements RolePermissionDao {

	@Override
	public List<RolePermission> findByRoleId(Long roleId) {
		Search s = new Search();
		s.addFilterEqual("role.id", roleId);
		return this.search(s);
	}

}
