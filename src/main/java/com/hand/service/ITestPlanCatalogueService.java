package com.hand.service;

import com.hand.entity.TestPlanCatalogue;

import java.util.List;

/**
 * Created by tuberose on 16-9-20.
 */
public interface ITestPlanCatalogueService {
    /*
    *	创建测试方案目录
     */
    public int createTestPlanCatalogue(TestPlanCatalogue testPlanCatalogue) throws Exception;

    /*
    *	删除测试方案目录
     */
    public void deleteTestPlanCatalogue(TestPlanCatalogue testPlanCatalogue) throws Exception;

    /*
    *	更新测试方案目录信息
     */
    public void updateTestPlanCatalogue(TestPlanCatalogue testPlanCatalogue) throws Exception;

    /*
    *	通过SQL 查找 测试方案目录 返回测试方案目录列表
    */
    public List<TestPlanCatalogue> FindBySQL(String sql) throws Exception;

    /*
    *	通过TestPlanCatalogue_id 查找 测试方案目录 返回测试方案目录列表
    */
    public TestPlanCatalogue FindByID(int testPlanCatalogueId) throws Exception;
}
