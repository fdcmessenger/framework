package org.sbbs.security.dao.hibernate;

import org.sbbs.base.dao.hibernate.BaseDAOImpl;
import org.sbbs.security.dao.LogEntityDao;
import org.sbbs.security.model.LogEntity;
import org.springframework.stereotype.Repository;

@Repository("logEntityDao")
public class LogEntityDaoHibernate extends BaseDAOImpl<LogEntity, Long>
		implements LogEntityDao {

}
