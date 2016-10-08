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
            System.out.println("由于参数导致创建失败--用户名或者是密码没有填写");
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

}