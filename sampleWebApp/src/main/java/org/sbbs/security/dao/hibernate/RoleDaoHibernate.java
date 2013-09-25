package org.sbbs.security.dao.hibernate;

import org.sbbs.base.dao.hibernate.BaseDAOImpl;
import org.sbbs.security.dao.RoleDao;
import org.sbbs.security.model.Role;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoHibernate extends BaseDAOImpl<Role, Long>
		implements RoleDao {

}
