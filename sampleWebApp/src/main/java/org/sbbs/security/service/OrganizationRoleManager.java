/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, ketayao.com
 * Filename:		com.ketayao.ketacustom.service.OrganizationRoleService.java
 * Class:			OrganizationRoleService
 * Date:			2013-4-15
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/

package org.sbbs.security.service;

import java.util.List;

import org.sbbs.base.service.IBaseManager;
import org.sbbs.security.model.OrganizationRole;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 2.0.0
 * @since 2013-4-15 下午4:14:43
 */

public interface OrganizationRoleManager extends
		IBaseManager<OrganizationRole, Long> {
	// OrganizationRole get(Long id);

	/**
	 * 根据organizationId，找到已分配的角色。 描述
	 * 
	 * @param organizationId
	 * @return
	 */
	List<OrganizationRole> findByOrgId(Long organizationId);

	// void save(OrganizationRole organizationRole);

	// void delete(Long organizationRoleId);
}
