package org.sbbs.security.dao.hibernate;

import org.sbbs.base.dao.hibernate.BaseDAOImpl;
import org.sbbs.security.dao.OrganizationDao;
import org.sbbs.security.model.Organization;
import org.springframework.stereotype.Repository;

@Repository("organizationDao")
public class OrganizationDaoHibernate extends BaseDAOImpl<Organization, Long>
		implements OrganizationDao {

}
