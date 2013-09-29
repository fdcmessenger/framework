/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.dao.ModuleDao.java
 * Class:			ModuleDao
 * Date:			2012-8-6
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package org.sbbs.security.dao;

import org.sbbs.base.dao.BaseTreeDao;
import org.sbbs.security.model.Module;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-6 上午9:31:03 
 */

public interface ModuleDao extends BaseTreeDao<Module, Long> {
/*	Page<Module> findByParentId(Long parentId, Pageable pageable);
	
	Page<Module> findByParentIdAndNameContaining(Long parentId, String name, Pageable pageable);
	
	@QueryHints(value={
			@QueryHint(name="org.hibernate.cacheable",value="true"),
			@QueryHint(name="org.hibernate.cacheRegion",value="com.ketayao.ketacustom.entity.main")
		}
	)
	@Query("from Module m order by m.priority ASC")
	List<Module> findAllWithCache();
	
	*//**
	 * 根据sn，查找Module
	 * 描述
	 * @param sn
	 * @return
	 *//*
	List<Module> findBySn(String sn);*/
}
