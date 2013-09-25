package org.sbbs.security.service.impl;

import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.LogEntityDao;
import org.sbbs.security.model.LogEntity;
import org.sbbs.security.service.LogEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logEntityManager")
public class LogEntityManagerImpl extends BaseManagerImpl<LogEntity, Long> implements LogEntityManager {
	LogEntityDao logEntityDao;

	@Autowired
	public LogEntityManagerImpl(LogEntityDao LogEntityDao) {
		super(LogEntityDao);
		this.logEntityDao = LogEntityDao;
	}
}
