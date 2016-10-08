package com.hand.service;

import com.hand.entity.TestPlan;

import java.util.List;

/**
 * Created by tuberose on 16-9-20.
 */
public interface ITestPlanService {
    /*
    *	创建测试方案
     */
    public void createTestPlan(TestPlan TestPlan) throws Exception;

    /*
    *	删除测试方案
     */
    public void deleteTestPlan(TestPlan TestPlan) throws Exception;

    /*
    *	更新测试方案
     */
    public void updateTestPlan(TestPlan TestPlan) throws Exception;

    /*
    *	通过SQL 查找 测试方案 返回测试方案列表
    */
    public List<TestPlan> FindBySQL(String sql) throws Exception;

    /*
    *	通过id 查找 测试方案 返回测试方案列表
    */
    public TestPlan FindByID(int TestPlanId) throws Exception;
}
