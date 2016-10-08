package com.hand.actions;

import com.hand.commonKey.CommonKey;
import com.hand.entity.TestPlan;
import com.hand.paging.PagingService;
import com.hand.service.ITestPlanService;


import javax.annotation.Resource;
import java.io.PrintWriter;

public class TestPlanAction extends BaseAction {

    @Resource(name = "testPlanService")
    private ITestPlanService testPlanService;

    @Resource(name = "pagingService")
    private PagingService<TestPlan> pagingTestPlanService;

    public void createTestPlan() throws Exception {
        TestPlan testPlan = new TestPlan();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》createTestPlan 方法");
        String planName = request.getParameter("planName");

        if (planName == null)
        {
            System.out.println("由于参数导致创建失败--方案名称没有写");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }

        testPlan.setPlanname(planName);

        try {
            testPlanService.createTestPlan(testPlan);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("创建成功");
        out.print(CommonKey.CREATESUCCESS);
    }

    public void updateTestPlan() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》updateTestPlan 方法");
        String planNameId = request.getParameter("planNameId");
        String planName = request.getParameter("planName");

        if (planNameId==null || planName == null)
        {
            System.out.println("由于参数导致创建失败--方案名称或者方案Id 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        TestPlan testPlan = testPlanService.FindByID(Integer.parseInt(planNameId));
        if(testPlan ==null){
            System.out.println("查询的测试方案不存在");
            return;
        }
        testPlan.setPlanname(planName);

        try {
            testPlanService.updateTestPlan(testPlan);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("修改成功");
        out.print(CommonKey.UPDATESUCCESS);
    }

    public void deleteTestPlan() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》deleteTestPlan 方法");
        String planNameId = request.getParameter("planNameId");

        if (planNameId==null)
        {
            System.out.println("由于参数导致创建失败-- 方案Id 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        TestPlan testPlan = testPlanService.FindByID(Integer.parseInt(planNameId));
        if(testPlan ==null){
            System.out.println("查询的测试方案不存在");
            return;
        }
        testPlan.setDeleteFlag(1);
        try {
            testPlanService.updateTestPlan(testPlan);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("删除成功");
        out.print(CommonKey.DELETESUCCESS);
    }
}