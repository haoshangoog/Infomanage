package com.hand.dao.impl;

import com.hand.dao.IFAQDao;
import com.hand.dao.common.AbstractHibernateDao;
import com.hand.entity.FAQ;
import org.springframework.stereotype.Repository;

@Repository("fAQDao")
public class FAQDao extends AbstractHibernateDao<FAQ> implements IFAQDao {
	public FAQDao() {
		super();
		setClazz(FAQ.class);
	}
}
