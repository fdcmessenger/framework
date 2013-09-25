/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.dao.LogEntityDao.java
 * Class:			LogEntityDao
 * Date:			2013-5-3
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package org.sbbs.security.dao;

import org.sbbs.base.dao.IBaseDAO;
import org.sbbs.security.model.LogEntity;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  2.1.0
 * @since   2013-5-3 下午5:06:37 
 */

public interface LogEntityDao extends IBaseDAO<LogEntity, Long>{
	//Page<LogEntity> findByLogLevel(LogLevel level, Pageable pageable);
}
