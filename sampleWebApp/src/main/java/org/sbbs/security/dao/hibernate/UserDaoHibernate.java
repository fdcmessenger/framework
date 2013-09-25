package org.sbbs.security.dao.hibernate;

import org.sbbs.base.dao.hibernate.BaseDAOImpl;
import org.sbbs.security.dao.UserDao;
import org.sbbs.security.model.User;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;

@Repository("userDao")
public class UserDaoHibernate extends BaseDAOImpl<User, Long> implements
		UserDao {

	@Override
	public User findByUsername(String Username) {
		Search s = new Search();
		s.addFilterEqual("username", Username);
		User u = this.searchUnique(s);
		return u;
	}

}
