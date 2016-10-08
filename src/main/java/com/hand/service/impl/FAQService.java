package com.hand.service.impl;

import com.hand.dao.IFAQDao;
import com.hand.entity.FAQ;
import com.hand.service.IFAQService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("fAQService")
public class FAQService implements IFAQService {

    @Resource(name = "fAQDao")
    private IFAQDao fAQDao;

    @Override
    public void createFAQ(FAQ faq) throws Exception {
        fAQDao.Create(faq);
    }

    @Override
    public void deleteFAQ(FAQ faq) throws Exception {
        fAQDao.Delete(faq);
    }

    @Override
    public void updateFAQ(FAQ faq) throws Exception {
        fAQDao.Update(faq);
    }

    @Override
    public List<FAQ> FindBySQL(String sql) throws Exception {
       return fAQDao.FindBySQL(sql);
    }

    @Override
    public FAQ FindByID(int faqId) throws Exception {
        return fAQDao.FindOne(faqId);
    }

}
