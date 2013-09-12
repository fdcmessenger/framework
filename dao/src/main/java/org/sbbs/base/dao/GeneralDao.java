package org.sbbs.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.sbbs.base.dao.hibernate.CallReturn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.googlecode.genericdao.dao.hibernate.GeneralDAO;

public interface GeneralDao extends GeneralDAO {
	//public HibernateTemplate getHibernateTemplate();
	public Session getCurrentSession();
	public JdbcTemplate getJdbcTemplate();

	public <T> boolean exists(Class<T> type, Serializable id);

	public CallReturn procedureCall(String procName, Object[] args);
	public List callQuery(String procName, Object[] args);
}
