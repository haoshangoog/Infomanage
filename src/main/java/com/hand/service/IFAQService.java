package com.hand.service;

import com.hand.entity.FAQ;

import java.util.List;

/**
 * Created by tuberose on 16-9-20.
 */
public interface IFAQService {
    /*
    *	创建常见问题解答
     */
    public void createFAQ(FAQ faq) throws Exception;

    /*
    *	删除常见问题解答
     */
    public void deleteFAQ(FAQ faq) throws Exception;

    /*
    *	更新常见问题解答信息
     */
    public void updateFAQ(FAQ faq) throws Exception;

    /*
    *	通过SQL 查找 常见问题解答 返回常见问题解答列表
    */
    public List<FAQ> FindBySQL(String sql) throws Exception;

    /*
    *	通过FAQ_id 查找 常见问题解答 返回常见问题解答列表
    */
    public FAQ FindByID(int faqId) throws Exception;
}
