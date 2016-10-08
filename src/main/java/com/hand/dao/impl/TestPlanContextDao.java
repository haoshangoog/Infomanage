package com.hand.dao.impl;

import com.hand.dao.ITestPlanContextDao;
import com.hand.dao.common.AbstractHibernateDao;
import com.hand.entity.TestPlanContext;
import org.springframework.stereotype.Repository;

@Repository("testPlanContextDao")
public class TestPlanContextDao extends AbstractHibernateDao<TestPlanContext> implements ITestPlanContextDao {
	public TestPlanContextDao() {
		super();
		setClazz(TestPlanContext.class);
	}
}
