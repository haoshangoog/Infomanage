package com.hand.service.impl;

import com.hand.dao.ITestPlanContextDao;
import com.hand.entity.TestPlanContext;
import com.hand.service.ITestPlanContextService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Transactional
@Service("testPlanContextService")
public class TestPlanContextService implements ITestPlanContextService {

    @Resource(name = "testPlanContextDao")
    private ITestPlanContextDao testPlanContextDao;

    @Override
    public int createTestPlanContext(TestPlanContext testPlanContext) throws Exception {
        return (int)testPlanContextDao.Create(testPlanContext);
    }

    @Override
    public void deleteTestPlanContext(TestPlanContext testPlanContext) throws Exception {
        testPlanContextDao.Delete(testPlanContext);
    }

    @Override
    public void updateTestPlanContext(TestPlanContext testPlanContext) throws Exception {
        testPlanContextDao.Update(testPlanContext);
    }

    @Override
    public List<TestPlanContext> FindBySQL(String sql) throws Exception {
       return testPlanContextDao.FindBySQL(sql);
    }

    @Override
    public TestPlanContext FindByID(int testPlanContextId) throws Exception {
        return testPlanContextDao.FindOne(testPlanContextId);
    }

}
