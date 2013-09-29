package org.sbbs.base.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sbbs.base.dao.IBaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
/*import org.springframework.orm.hibernate3.HibernateTemplate;*/
import org.springframework.orm.hibernate4.SessionFactoryUtils;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

public class BaseDAOImpl<T, ID extends Serializable> extends GenericDAOImpl<T, ID> implements IBaseDAO<T, ID> {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	public Session getSession() {
		return super.getSession();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public boolean exists(ID id) {
		T entity = this.find(id);
		return entity != null;
	}

	@Override
	public void remove(List<T> list) {

		T[] array = (T[]) Array.newInstance(this.persistentClass, list.size());
		list.toArray(array);

		this.remove(array);

	}

}
