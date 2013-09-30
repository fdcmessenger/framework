package org.sbbs.security.dao.hibernate;

import org.sbbs.base.dao.hibernate.BaseTreeDaoHibernate;
import org.sbbs.security.dao.OrganizationDao;
import org.sbbs.security.model.Organization;
import org.springframework.stereotype.Repository;

@Repository("organizationDao")
public class OrganizationDaoHibernate extends BaseTreeDaoHibernate<Organization, Long>
		implements OrganizationDao {

}
