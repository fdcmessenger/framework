/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.service.UserManager.java
 * Class:			UserManager
 * Date:			2012-8-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:
 *
 * </pre>
 **/

package org.sbbs.security.service;

import org.hibernate.service.spi.ServiceException;
import org.sbbs.base.service.IBaseManager;
import org.sbbs.security.exception.ExistedException;
import org.sbbs.security.model.User;

/**
 *
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-7 下午3:03:59
 */

public interface UserManager extends IBaseManager<User, Long>{

	User getByUserName(String username);
	public void restPsw(User user, String restPsw);
	void updatePwd(User user, String newPwd) throws ServiceException;
	public void updateUser(User user) ;
	public void insertUser(User user) throws ExistedException ;
	/*List<User> find(Page page, String name);

	void update(User user);

	void updatePwd(User user, String newPwd) throws ManagerException;

	void save(User user) throws ExistedException;

	User get(Long id);

	void delete(Long id) throws ManagerException;

	List<User> findAll(Page page);*/
}
