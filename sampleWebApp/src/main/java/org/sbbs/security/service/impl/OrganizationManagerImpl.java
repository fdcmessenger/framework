package org.sbbs.security.service.impl;

import org.sbbs.base.service.impl.BaseTreeManagerImpl;
import org.sbbs.security.dao.OrganizationDao;
import org.sbbs.security.model.Organization;
import org.sbbs.security.service.OrganizationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("organizationManager")
public class OrganizationManagerImpl extends BaseTreeManagerImpl<Organization, Long>
		implements OrganizationManager {
	OrganizationDao organizationDao;

	@Autowired
	public OrganizationManagerImpl(OrganizationDao organizationDao) {
		super(organizationDao);
		this.organizationDao = organizationDao;
	}
}
