package org.sbbs.demo.service.impl;

import org.sbbs.base.service.impl.BaseManagerImpl;
import org.sbbs.demo.dao.DemoEntityDao;
import org.sbbs.demo.model.DemoEntity;
import org.sbbs.demo.service.DemoEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service( "demoEntityManager")
public class DemoEntityManagerImpl extends BaseManagerImpl<DemoEntity, Long>
    implements DemoEntityManager {
    DemoEntityDao demoEntityDao;

    @Autowired
    public DemoEntityManagerImpl(DemoEntityDao demoEntityDao) {
        super(demoEntityDao);
        this.demoEntityDao = demoEntityDao;
    }
}
