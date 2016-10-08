package com.hand.service;

import com.hand.entity.TestPlanContext;

import java.util.List;

/**
 * Created by tuberose on 16-9-20.
 */
public interface ITestPlanContextService {
    /*
    *	创建测试方案内容页
     */
    public void createTestPlanContext(TestPlanContext testPlanContext) throws Exception;

    /*
    *	删除测试方案内容页
     */
    public void deleteTestPlanContext(TestPlanContext testPlanContext) throws Exception;

    /*
    *	更新测试方案内容页信息
     */
    public void updateTestPlanContext(TestPlanContext testPlanContext) throws Exception;

    /*
    *	通过SQL 查找 测试方案内容页 返回测试方案内容页列表
    */
    public List<TestPlanContext> FindBySQL(String sql) throws Exception;

    /*
    *	通过TestPlanContext_id 查找 测试方案内容页 返回测试方案内容页列表
    */
    public TestPlanContext FindByID(int testPlanContextId) throws Exception;
}
