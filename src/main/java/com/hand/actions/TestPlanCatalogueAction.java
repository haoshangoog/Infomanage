package com.hand.actions;

import com.hand.commonKey.CommonKey;
import com.hand.entity.TestPlanCatalogue;
import com.hand.paging.PagingService;
import com.hand.service.ITestPlanCatalogueService;

import javax.annotation.Resource;
import java.io.PrintWriter;

public class TestPlanCatalogueAction extends BaseAction {

    @Resource(name = "testPlanCatalogueService")
    private ITestPlanCatalogueService testPlanCatalogueService;

    @Resource(name = "pagingService")
    private PagingService<TestPlanCatalogue> pagingTestPlanCatalogueService;

    public void createTestPlanCatalogue() throws Exception {
        TestPlanCatalogue testPlanCatalogue = new TestPlanCatalogue();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》createTestPlanCatalogue 方法");
        String catalogueName = request.getParameter("catalogueName");
        String parentsId     = request.getParameter("parentsId");
        String sequence      = request.getParameter("sequence");
        if (catalogueName == null ||parentsId == null || sequence == null)
        {
            System.out.println("由于参数导致创建失败--catalogueName/parentsId/sequence 没有写");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        TestPlanCatalogue testPlanCatalogueParents = testPlanCatalogueService.FindByID(Integer.parseInt(parentsId));
        if(testPlanCatalogueParents==null){
            System.out.println("创建子目录时找不到父目录");
            out.print(CommonKey.CREATFAIL);
            return;
        }
        testPlanCatalogue.setCatalogueName(catalogueName);
        testPlanCatalogue.setTestPlanId(testPlanCatalogueParents.getTestPlanId());
        testPlanCatalogue.setParentsId(Integer.parseInt(parentsId));
        testPlanCatalogue.setSequence(Integer.parseInt(sequence));

        try {
            testPlanCatalogueService.createTestPlanCatalogue(testPlanCatalogue);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("创建成功");
        out.print(CommonKey.CREATESUCCESS);
    }

    public void updateTestPlanCatalogue() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》updateTestPlanCatalogue 方法");
        String catalogueId   = request.getParameter("catalogueId");
        String catalogueName = request.getParameter("catalogueName");
        String parentsId     = request.getParameter("parentsId");
        String sequence      = request.getParameter("sequence");

        if (catalogueId==null)
        {
            System.out.println("由于参数导致创建失败--catalogueId 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        TestPlanCatalogue testPlanCatalogue = testPlanCatalogueService.FindByID(Integer.parseInt(catalogueId));
        if(testPlanCatalogue ==null){
            System.out.println("更新的目录不存在");
            return;
        }
        if(catalogueName != null) testPlanCatalogue.setCatalogueName(catalogueName);
        if(parentsId     != null) testPlanCatalogue.setParentsId(Integer.parseInt(parentsId));
        if(sequence      != null) testPlanCatalogue.setSequence(Integer.parseInt(sequence));

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
}