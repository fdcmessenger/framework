package org.sbbs.security.dao.hibernate;

import java.util.List;

import org.sbbs.base.dao.hibernate.BaseDAOImpl;
import org.sbbs.security.dao.UserRoleDao;
import org.sbbs.security.model.UserRole;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;

@Repository("userRoleDao")
public class UserRoleDaoHibernate extends BaseDAOImpl<UserRole, Long> implements UserRoleDao {

	@Override
	public List<UserRole> findByUserId(Long userId) {
		Search s = new Search();
		s.addFilterEqual("user.id", userId);
		return this.search(s);
	}

}
