package com.hand.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hand.commonKey.CommonKey;
import com.hand.entity.TestPlanCatalogue;
import com.hand.entity.TestPlanContext;
import com.hand.paging.PagingService;
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

    @Resource(name = "pagingService")
    private PagingService<TestPlanCatalogue> pagingTestPlanCatalogueService;

    public void updateTestPlanForm() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》updateTestPlanCatalogue 方法");

        String testPlanForm =request.getParameter("testPlanForm");
        Gson gson = new Gson();
        TestPlanCatalogue testPlanCatalogue = gson.fromJson(testPlanForm,TestPlanCatalogue.class);

        TestPlanCatalogue testPlanCatalogue2 = null;
        if(testPlanCatalogue.getId() == 0){
            int testPlanContext_id = testPlanContextService.createTestPlanContext(new TestPlanContext());
            testPlanCatalogue.setTestPlanContextId(testPlanContext_id);
            int testPlanCatalogue_id = testPlanCatalogueService.createTestPlanCatalogue(testPlanCatalogue);
            testPlanCatalogue2 = testPlanCatalogueService.FindByID(testPlanCatalogue_id);
        }else{
            testPlanCatalogue2 = testPlanCatalogueService.FindByID(testPlanCatalogue.getId());
        }
        if(testPlanCatalogue ==null){
            System.out.println("更新的目录不存在");
            return;
        }
        TestPlanContext testPlanContext = testPlanContextService.FindByID(testPlanCatalogue2.getTestPlanContextId());
        testPlanContext.setTestPlanContext(testPlanCatalogue.getTestPlanContext().getTestPlanContext());
        testPlanContextService.updateTestPlanContext(testPlanContext);

        try {
            testPlanCatalogueService.updateTestPlanCatalogue(testPlanCatalogue);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("修改成功");
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

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        out.print(gson.toJson(testPlanCatalogue));

    }

    // 获取目录的子目录
    public void selectChildCatalogue() throws Exception{
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》selectChildCatalogue 方法");
        String parentsCatalogueId   = request.getParameter("parentsCatalogueId");
        String deleteFlag   = request.getParameter("deleteFlag");
        String sql ="";
        if(deleteFlag ==null){
            sql ="SELECT * FROM testPlanCatalogue " +
                    "where parentsId = "+Integer.parseInt(parentsCatalogueId)+" and deleteflag=0" +
                    " order by sequence";
        }else {
            sql ="SELECT * FROM testPlanCatalogue " +
                    "where parentsId = "+Integer.parseInt(parentsCatalogueId)+" and deleteflag="+Integer.parseInt(deleteFlag)+
                    " order by sequence";
        }
        System.out.println("selectChildCatalogue SQL 查询语句："+sql);
        List<TestPlanCatalogue> testPlanCatalogueList = testPlanCatalogueService.FindBySQL(sql);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        out.print(gson.toJson(testPlanCatalogueList));
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
        if (testPlanCatalogueList != null){
            testPlanCatalogueList = getAllCatalogue(testPlanCatalogueList.get(0).getId());
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
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