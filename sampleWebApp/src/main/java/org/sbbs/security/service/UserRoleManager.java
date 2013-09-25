/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.service.UserRoleService.java
 * Class:			UserRoleService
 * Date:			2012-8-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package org.sbbs.security.service;

import java.util.List;

import org.sbbs.base.service.IBaseManager;
import org.sbbs.security.model.UserRole;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @since 2012-8-7 下午5:08:51
 */

public interface UserRoleManager extends IBaseManager<UserRole, Long> {
	/*
	 * UserRole get(Long id);
	 */
	/**
	 * 根据userId，找到已分配的角色。 描述
	 * 
	 * @param userId
	 * @return
	 */
	List<UserRole> findByUserId(Long userId);

	/*
	 * * void save(UserRole userRole);
	 * 
	 * void delete(Long userRoleId);
	 */

}
