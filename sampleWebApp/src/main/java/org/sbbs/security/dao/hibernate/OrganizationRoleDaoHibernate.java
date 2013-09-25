package org.sbbs.security.dao.hibernate;

import java.util.List;

import org.sbbs.base.dao.hibernate.BaseDAOImpl;
import org.sbbs.security.dao.OrganizationRoleDao;
import org.sbbs.security.model.OrganizationRole;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;

@Repository("organizationRoleDao")
public class OrganizationRoleDaoHibernate extends BaseDAOImpl<OrganizationRole, Long> implements OrganizationRoleDao {

	@Override
	public List<OrganizationRole> findByOrganizationId(Long organizationId) {
		Search s = new Search();
		s.addFilterEqual("organization.id", organizationId);
		return this.search(s);
	}

}
