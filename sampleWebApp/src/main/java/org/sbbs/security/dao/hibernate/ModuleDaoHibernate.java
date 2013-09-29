package org.sbbs.security.dao.hibernate;

import org.sbbs.base.dao.hibernate.BaseTreeDaoHibernate;
import org.sbbs.security.dao.ModuleDao;
import org.sbbs.security.model.Module;
import org.springframework.stereotype.Repository;

@Repository("moduleDao")
public class ModuleDaoHibernate extends BaseTreeDaoHibernate<Module, Long> implements ModuleDao {

}
