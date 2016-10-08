package com.hand.dao.impl;

import com.hand.dao.ITestPlanDao;
import com.hand.dao.common.AbstractHibernateDao;
import com.hand.entity.TestPlan;
import org.springframework.stereotype.Repository;

@Repository("testPlanDao")
public class TestPlanDao extends AbstractHibernateDao<TestPlan> implements ITestPlanDao {
	public TestPlanDao() {
		super();
		setClazz(TestPlan.class);
	}
}
