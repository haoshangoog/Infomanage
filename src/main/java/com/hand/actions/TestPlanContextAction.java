package com.hand.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hand.commonKey.CommonKey;
import com.hand.entity.TestPlanContext;
import com.hand.service.ITestPlanContextService;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.List;

public class TestPlanContextAction extends BaseAction {

    @Resource(name = "testPlanContextService")
    private ITestPlanContextService testPlanContextService;

    public void createTestPlanContext() throws Exception {
        TestPlanContext testPlanContext = new TestPlanContext();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》createTestPlanContext 方法");
        String catalogueId      = request.getParameter("catalogueId");

        String testID           = request.getParameter("testID");
        String testContext      = request.getParameter("testContext");
        String testAim          = request.getParameter("testAim");
        String testMethod       = request.getParameter("testMethod");
        String testTopology     = request.getParameter("testTopology");
        String testConfigure    = request.getParameter("testConfigure");
        String testResult       = request.getParameter("testResult");
        String northInterface   = request.getParameter("northInterface");
        String testConclusion   = request.getParameter("testConclusion");
        String remark           = request.getParameter("remark");

        if (catalogueId == null)
        {
            System.out.println("由于参数导致创建失败--catalogueId 没有写");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }

        testPlanContext.setCatalogueId(Integer.parseInt(catalogueId));
        testPlanContext.setTestID(testID);
        testPlanContext.setTestContext(testContext);
        testPlanContext.setTestAim(testAim);
        testPlanContext.setTestMethod(testMethod);
        testPlanContext.setTestTopology(testTopology);
        testPlanContext.setTestConfigure(testConfigure);
        testPlanContext.setTestResult(testResult);
        testPlanContext.setNorthInterface(northInterface);
        testPlanContext.setTestConclusion(testConclusion);
        testPlanContext.setRemark(remark);

        try {
            testPlanContextService.createTestPlanContext(testPlanContext);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("创建成功");
        out.print(CommonKey.CREATESUCCESS);
    }

    public void updateTestPlanContext() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》updateTestPlanContext 方法");
        String testPlanContextId= request.getParameter("testPlanContextId");

        String testID           = request.getParameter("testID");
        String testContext      = request.getParameter("testContext");
        String testAim          = request.getParameter("testAim");
        String testMethod       = request.getParameter("testMethod");
        String testTopology     = request.getParameter("testTopology");
        String testConfigure    = request.getParameter("testConfigure");
        String testResult       = request.getParameter("testResult");
        String northInterface   = request.getParameter("northInterface");
        String testConclusion   = request.getParameter("testConclusion");
        String remark           = request.getParameter("remark");

        if (testPlanContextId==null)
        {
            System.out.println("由于参数导致创建失败--testPlanContextId 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        TestPlanContext testPlanContext = testPlanContextService.FindByID(Integer.parseInt(testPlanContextId));
        if(testPlanContext ==null){
            System.out.println("更新的测试方案内容页不存在");
            return;
        }
        if(testID           !=  null)   testPlanContext.setTestID(testID);
        if(testContext      !=  null)   testPlanContext.setTestContext(testContext);
        if(testAim          !=  null)   testPlanContext.setTestAim(testAim);
        if(testMethod       !=  null)   testPlanContext.setTestMethod(testMethod);
        if(testTopology     !=  null)   testPlanContext.setTestTopology(testTopology);
        if(testConfigure    !=  null)   testPlanContext.setTestConfigure(testConfigure);
        if(testResult       !=  null)   testPlanContext.setTestResult(testResult);
        if(northInterface   !=  null)   testPlanContext.setNorthInterface(northInterface);
        if(testConclusion   !=  null)   testPlanContext.setTestConclusion(testConclusion);
        if(remark           !=  null)   testPlanContext.setRemark(remark);

        try {
            testPlanContextService.updateTestPlanContext(testPlanContext);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("修改成功");
        out.print(CommonKey.UPDATESUCCESS);
    }

    public void deleteTestPlanContext() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》deleteTestPlanContext 方法");
        String testPlanContextId= request.getParameter("testPlanContextId");

        if (testPlanContextId==null)
        {
            System.out.println("由于参数导致创建失败-- 方案Id 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        TestPlanContext testPlanContext = testPlanContextService.FindByID(Integer.parseInt(testPlanContextId));
        if(testPlanContext ==null){
            System.out.println("删除的测试方案不存在");
            return;
        }
        try {
            testPlanContextService.deleteTestPlanContext(testPlanContext);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("删除成功");
        out.print(CommonKey.DELETESUCCESS);
    }

    public void selectContextByCatalogueId() throws Exception {
        System.out.print("---》selectContextByCatalogueId 方法");
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String catalogueId   = request.getParameter("catalogueId");

        if(catalogueId==null){
            System.out.println("由于参数导致创建失败-- catalogueId 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }

        List<TestPlanContext> TPcontext = testPlanContextService.FindBySQL("SELECT * FROM testPlanContext where catalogueId = "+Integer.parseInt(catalogueId));
        if(TPcontext.size()==0){
            out.print(CommonKey.NO_TESTPLANCONTEXT);
            return;
        }else {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            out.print(gson.toJson(TPcontext));
        }
    }
}