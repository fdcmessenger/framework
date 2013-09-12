package org.sbbs.base.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface IBaseDAO<T, ID extends Serializable> extends GenericDAO<T, ID> {

	/**
	 * 活的HibernateTemplate助手类,以供在需要的时候方便使用
	 */
//	public HibernateTemplate getHibernateTemplate();

	
	public Session getCurrentSession();
	/**
	 * 活的JdbcTemplate助手类,以供在需要的时候方便使用
	 */
	public JdbcTemplate getJdbcTemplate();

	/**
	 * 判断主键为id的实体是否存在
	 */
	public boolean exists(ID id);
}
