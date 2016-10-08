package com.hand.dao.impl;

import com.hand.dao.ITestPlanCatalogueDao;
import com.hand.dao.common.AbstractHibernateDao;
import com.hand.entity.TestPlanCatalogue;
import org.springframework.stereotype.Repository;

@Repository("testPlanCatalogueDao")
public class TestPlanCatalogueDao extends AbstractHibernateDao<TestPlanCatalogue> implements ITestPlanCatalogueDao {
	public TestPlanCatalogueDao() {
		super();
		setClazz(TestPlanCatalogue.class);
	}
}
