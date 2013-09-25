package org.sbbs.security.service.impl;

import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.ModuleDao;
import org.sbbs.security.model.Module;
import org.sbbs.security.service.ModuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("moduleManager")
public class ModuleManagerImpl extends BaseManagerImpl<Module, Long> implements
		ModuleManager {
	ModuleDao moduleDao;

	@Autowired
	public ModuleManagerImpl(ModuleDao dao) {
		super(dao);
		this.moduleDao = dao;
	}
}
