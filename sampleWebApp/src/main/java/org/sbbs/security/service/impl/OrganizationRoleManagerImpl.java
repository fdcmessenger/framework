package org.sbbs.security.service.impl;

import java.util.List;

import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.security.dao.OrganizationRoleDao;
import org.sbbs.security.model.OrganizationRole;
import org.sbbs.security.service.OrganizationRoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("organizationRoleManager")
public class OrganizationRoleManagerImpl extends BaseManagerImpl<OrganizationRole, Long> implements
		OrganizationRoleManager {
	OrganizationRoleDao organizationRoleDao;

	@Autowired
	public OrganizationRoleManagerImpl(OrganizationRoleDao organizationRoleDao) {
		super(organizationRoleDao);
		this.organizationRoleDao = organizationRoleDao;
	}

	@Override
	public List<OrganizationRole> findByOrgId(Long organizationId) {
		return this.organizationRoleDao.findByOrganizationId(organizationId);
	}
}
