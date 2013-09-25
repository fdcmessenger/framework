/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, ketayao.com
 * Filename:		com.ketayao.ketacustom.service.RolePermission.java
 * Class:			RolePermission
 * Date:			2013-4-16
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package org.sbbs.security.service;

import java.util.List;

import org.sbbs.base.service.IBaseManager;
import org.sbbs.security.model.RolePermission;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  2.0.0
 * @since   2013-4-16 下午2:11:48 
 */

public interface RolePermissionManager extends IBaseManager<RolePermission, Long>{
/*	void save(RolePermission rolePermission);
	
	RolePermission get(Long id);
	
	void update(RolePermission rolePermission);
	
	void delete(Long id);*/

	List<RolePermission> findByRoleId(Long roleId);
}
