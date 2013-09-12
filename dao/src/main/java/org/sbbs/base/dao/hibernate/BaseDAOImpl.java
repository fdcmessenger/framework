package org.sbbs.base.dao.hibernate;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.sbbs.base.dao.IBaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

public class BaseDAOImpl<T, ID extends Serializable> extends
		GenericDAOImpl<T, ID> implements IBaseDAO<T, ID> {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private HibernateTemplate hibernateTemplate;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(
				SessionFactoryUtils.getDataSource(sessionFactory));
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public boolean exists(ID id) {
		T entity = this.find(id);
		return entity != null;
	}

}
