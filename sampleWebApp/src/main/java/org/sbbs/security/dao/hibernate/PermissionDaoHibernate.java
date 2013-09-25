package org.sbbs.security.dao.hibernate;

import org.sbbs.base.dao.hibernate.BaseDAOImpl;
import org.sbbs.security.dao.PermissionDao;
import org.sbbs.security.model.Permission;
import org.springframework.stereotype.Repository;

@Repository("permissionDao")
public class PermissionDaoHibernate extends BaseDAOImpl<Permission, Long>
		implements PermissionDao {

}
