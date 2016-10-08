package com.hand.service.impl;

import com.hand.dao.ITestPlanCatalogueDao;
import com.hand.entity.TestPlanCatalogue;
import com.hand.service.ITestPlanCatalogueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("testPlanCatalogueService")
public class TestPlanCatalogueService implements ITestPlanCatalogueService {

    @Resource(name = "testPlanCatalogueDao")
    private ITestPlanCatalogueDao testPlanCatalogueDao;

    @Override
    public void createTestPlanCatalogue(TestPlanCatalogue testPlanCatalogue) throws Exception {
        testPlanCatalogueDao.Create(testPlanCatalogue);
    }

    @Override
    public void deleteTestPlanCatalogue(TestPlanCatalogue testPlanCatalogue) throws Exception {
        testPlanCatalogueDao.Delete(testPlanCatalogue);
    }

    @Override
    public void updateTestPlanCatalogue(TestPlanCatalogue testPlanCatalogue) throws Exception {
        testPlanCatalogueDao.Update(testPlanCatalogue);
    }

    @Override
    public List<TestPlanCatalogue> FindBySQL(String sql) throws Exception {
       return testPlanCatalogueDao.FindBySQL(sql);
    }

    @Override
    public TestPlanCatalogue FindByID(int testPlanCatalogueId) throws Exception {
        return testPlanCatalogueDao.FindOne(testPlanCatalogueId);
    }

}
