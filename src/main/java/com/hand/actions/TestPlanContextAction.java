package com.hand.actions;

import com.hand.commonKey.CommonKey;
import com.hand.entity.TestPlanContext;
import com.hand.service.ITestPlanContextService;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.List;

public class TestPlanContextAction extends BaseAction {

    @Resource(name = "testPlanContextService")
    private ITestPlanContextService testPlanContextService;

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
            out.print(gson.toJson(TPcontext));
        }
    }
}