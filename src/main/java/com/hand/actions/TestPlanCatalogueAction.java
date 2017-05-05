package com.hand.actions;

import com.google.gson.Gson;
import com.hand.commonKey.CommonKey;
import com.hand.entity.TestPlanCatalogue;
import com.hand.entity.TestPlanContext;
import com.hand.service.ITestPlanCatalogueService;
import com.hand.service.ITestPlanContextService;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.List;

public class TestPlanCatalogueAction extends BaseAction {

    @Resource(name = "testPlanCatalogueService")
    private ITestPlanCatalogueService testPlanCatalogueService;

    @Resource(name = "testPlanContextService")
    private ITestPlanContextService testPlanContextService;

    public void updateTestPlanForm() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》updateTestPlanCatalogue 方法");

        String tpFormReq =request.getParameter("testPlanForm");
        TestPlanCatalogue tpCatalogueReq = new Gson().fromJson(tpFormReq,TestPlanCatalogue.class);

        TestPlanCatalogue testPlanCatalogue =
                testPlanCatalogueService.FindByID(tpCatalogueReq.getId());

        if( null == testPlanCatalogue ){
            System.out.println("更新的目录不存在,需要创建");
            int tpcID = testPlanContextService.createTestPlanContext(tpCatalogueReq.getTestPlanContext());
            tpCatalogueReq.setTestPlanContextId(tpcID);
            testPlanCatalogueService.createTestPlanCatalogue(tpCatalogueReq);

        }else {
            System.out.println("更新的目录存在,更新");
            TestPlanContext testPlanContext = testPlanContextService.FindByID(testPlanCatalogue.getTestPlanContextId());
            testPlanContext.setTestPlanContext(tpCatalogueReq.getTestPlanContext().getTestPlanContext());
            testPlanContextService.updateTestPlanContext(testPlanContext);
            testPlanCatalogueService.updateTestPlanCatalogue(tpCatalogueReq);

        }

        System.out.println("修改／添加成功");
        out.print(CommonKey.UPDATESUCCESS);
    }

    public void deleteTestPlanCatalogue() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》deleteTestPlanCatalogue 方法");
        String catalogueId   = request.getParameter("catalogueId");

        if (catalogueId==null)
        {
            System.out.println("由于参数导致创建失败-- catalogueId 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        TestPlanCatalogue testPlanCatalogue = testPlanCatalogueService.FindByID(Integer.parseInt(catalogueId));
        if(testPlanCatalogue ==null){
            System.out.println("删除的目录不存在");
            return;
        }
        testPlanCatalogue.setDeleteFlag(1);
        try {
            testPlanCatalogueService.updateTestPlanCatalogue(testPlanCatalogue);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("删除成功");
        out.print(CommonKey.DELETESUCCESS);
    }

    public void getTestPlanForm() throws Exception{
        System.out.print("---》getTestContextPlanForm 方法");
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String catalogueId   = request.getParameter("catalogueId");

        if(catalogueId==null){
            System.out.println("由于参数导致创建失败-- catalogueId 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        TestPlanCatalogue testPlanCatalogue = testPlanCatalogueService.FindByID(Integer.parseInt(catalogueId));
        testPlanCatalogue.setTestPlanContext(testPlanContextService.FindByID(testPlanCatalogue.getTestPlanContextId()));

        out.print(gson.toJson(testPlanCatalogue));

    }

    public void getAllCatalogue() throws Exception{
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》getAllCatalogue 方法");
        String testPlanId   = request.getParameter("testPlanId");
        String sql ="";
        sql ="SELECT * FROM testPlanCatalogue " +
                "where parentsId=0 and sequence=0 and testPlanId="+Integer.parseInt(testPlanId)+" and deleteflag=0";
        System.out.println("selectFristCatalogue SQL 查询语句："+sql);
        List<TestPlanCatalogue> testPlanCatalogueList = testPlanCatalogueService.FindBySQL(sql);
        TestPlanCatalogue getAllTestPlanCatalogue = null;
        if (testPlanCatalogueList != null){
            testPlanCatalogueList.get(0).setChildren(getAllCatalogue((testPlanCatalogueList.get(0)).getId()));
        }
        out.print(gson.toJson(testPlanCatalogueList));
    }

    public List<TestPlanCatalogue> getAllCatalogue(int parentId) throws Exception {
        String sql = "SELECT * FROM testPlanCatalogue " +
                "where parentsId = " + parentId + " and deleteflag=0" +
                " order by sequence";
        System.out.print(sql);
        List<TestPlanCatalogue> testPlanCatalogueList = testPlanCatalogueService.FindBySQL(sql);
        if(testPlanCatalogueList == null){
            return null;
        }else {
            for (int i = 0 ;i < testPlanCatalogueList.size() ;i++) {
                List<TestPlanCatalogue> children = getAllCatalogue(testPlanCatalogueList.get(i).getId());
                if(children != null){
                    testPlanCatalogueList.get(i).setChildren(children);
                }
            }
            return testPlanCatalogueList;
        }
    }
}