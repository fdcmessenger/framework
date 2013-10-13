/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.service.OrganizationService.java
 * Class:			OrganizationService
 * Date:			2012-8-27
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:
 *
 * </pre>
 **/

package org.sbbs.security.service;

import java.util.List;

import org.sbbs.base.service.BaseTreeManager;
import org.sbbs.security.model.Organization;

/**
 *
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-27 下午3:56:25
 */

public interface OrganizationManager extends BaseTreeManager<Organization, Long>{
/*
	List<Organization> find(Long parentId, Page page);

	List<Organization> find(Long parentId, String name, Page page);

	Organization getTree();

	void save(Organization organization);

	Organization get(Long id);

	void update(Organization organization);

	void delete(Long id) throws ServiceException;*/

	/*List<Organization> 	findAllExceptSelf(Long selfId);*/
}
