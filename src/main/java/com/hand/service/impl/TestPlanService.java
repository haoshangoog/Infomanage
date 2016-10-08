package com.hand.service.impl;

import com.hand.dao.ITestPlanDao;
import com.hand.entity.TestPlan;
import com.hand.service.ITestPlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("testPlanService")
public class TestPlanService implements ITestPlanService {

    @Resource(name = "testPlanDao")
    private ITestPlanDao testPlanDao;

    @Override
    public void createTestPlan(TestPlan testPlan) throws Exception {
        testPlanDao.Create(testPlan);
    }

    @Override
    public void deleteTestPlan(TestPlan testPlan) throws Exception {
        testPlanDao.Delete(testPlan);
    }

    @Override
    public void updateTestPlan(TestPlan testPlan) throws Exception {
        testPlanDao.Update(testPlan);
    }

    @Override
    public List<TestPlan> FindBySQL(String sql) throws Exception {
       return testPlanDao.FindBySQL(sql);
    }

    @Override
    public TestPlan FindByID(int testPlanId) throws Exception {
        return testPlanDao.FindOne(testPlanId);
    }

}
