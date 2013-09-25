/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.dao.UserRoleDao.java
 * Class:			UserRoleDao
 * Date:			2012-8-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package org.sbbs.security.dao;

import java.util.List;

import org.sbbs.base.dao.IBaseDAO;
import org.sbbs.security.model.UserRole;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @since 2012-8-7 下午5:08:15
 */

public interface UserRoleDao extends IBaseDAO<UserRole, Long> {
	 List<UserRole> findByUserId(Long userId);
}
