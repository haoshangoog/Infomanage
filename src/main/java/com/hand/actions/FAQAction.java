package com.hand.actions;

import com.hand.commonKey.CommonKey;
import com.hand.entity.FAQ;
import com.hand.paging.Pager;
import com.hand.paging.PagingService;
import com.hand.service.IFAQService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;
import java.io.PrintWriter;

public class FAQAction extends BaseAction {

    @Resource(name = "fAQService")
    private IFAQService fAQService;

    @Resource(name = "pagingService")
    private PagingService<FAQ> pagingFAQService;

    public void createFAQ() throws Exception {
        FAQ faq = new FAQ();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》createFAQ 方法");
        String question = request.getParameter("question");
        String answer   = request.getParameter("answer");

        if (question == null) {
            System.out.println("由于参数导致创建失败--question 没有写");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }

        faq.setQuestion(question);
        faq.setAnswer(answer);

        try {
            fAQService.createFAQ(faq);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("创建成功");
        out.print(CommonKey.CREATESUCCESS);
    }

    public void updateFAQ() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》updateFAQ 方法");
        String faqId    = request.getParameter("faqId");
        String question = request.getParameter("question");
        String answer   = request.getParameter("answer");

        if (faqId == null) {
            System.out.println("由于参数导致创建失败--faqId 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        FAQ faq = fAQService.FindByID(Integer.parseInt(faqId));
        if (faq == null) {
            System.out.println("更新的FAQ不存在");
            return;
        }
        if( null != question || ("").equals(question)) faq.setQuestion(question);
        if( null != answer   || ("").equals(answer))   faq.setAnswer(answer);

        try {
            fAQService.updateFAQ(faq);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("修改成功");
        out.print(CommonKey.UPDATESUCCESS);
    }

    public void deleteFAQ() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》deleteFAQ 方法");
        String faqId    = request.getParameter("faqId");

        if (faqId == null) {
            System.out.println("由于参数导致创建失败-- faqId 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        FAQ faq = fAQService.FindByID(Integer.parseInt(faqId));
        if (faq == null) {
            System.out.println("更新的FAQ不存在");
            return;
        }
        faq.setDeleteFlag(1);
        try {
            fAQService.updateFAQ(faq);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("删除成功");
        out.print(CommonKey.DELETESUCCESS);
    }

    public void pageingFAQ() throws Exception {
        System.out.print("---》pageingTestPlan 方法");
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        // 测试方案 的分页查询
        String pageNo   = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        String deleteFlag = request.getParameter("deleteFlag");

        if (pageNo == null || pageSize == null) {
            System.out.println("由于参数导致创建失败--pageNo / pageSize 缺失");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }

        Criterion criterion = null;
        if (deleteFlag != null) {
            criterion = Restrictions.eq("deleteFlag", Integer.parseInt(deleteFlag));
        } else {
            criterion = Restrictions.eq("deleteFlag", 0);
        }
        pagingFAQService.PagingService(FAQ.class);
        Pager pager = pagingFAQService.paging(Integer.parseInt(pageNo), Integer.parseInt(pageSize), criterion);

        System.out.println("数据：" + pager.toString());
        out.print(gson.toJson(pager));
        System.out.println("发送数据==" + gson.toJson(pager));
    }

    public void selectFAQById() throws Exception {
        System.out.println("---》selectFAQById 方法");
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String FAQID = request.getParameter("FAQID");
        if (null == FAQID || "".equals(FAQID)) {
            System.out.print("FAQID 不正确");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        FAQ faq = null;
        try {
            faq = fAQService.FindByID(Integer.parseInt(FAQID));
        } catch (Exception e) {
            System.out.println("深层调用导致登陆失败");
            out.print(CommonKey.DELETESUCCESS);
            e.printStackTrace();
            return;
        }
        out.print(gson.toJson(faq));
    }

}