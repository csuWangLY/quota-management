package com.project.quotamanagement.quotamanagement.service.impl;

import com.project.quotamanagement.quotamanagement.dao.QuotaAccountDao;
import com.project.quotamanagement.quotamanagement.dao.UserAccountDao;
import com.project.quotamanagement.quotamanagement.model.QuotaAccount;
import com.project.quotamanagement.quotamanagement.model.UserAccount;
import com.project.quotamanagement.quotamanagement.service.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class UserAccountRepositoryImpl implements UserAccountRepository {

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private QuotaAccountDao quotaAccountDao;


    @Override
    public UserAccount accountQuery(long userAccountId, String quotaAccountType) {
        UserAccount userAccount = userAccountDao.queryAccount(userAccountId);
        List<QuotaAccount> quotaAccountList = quotaAccountDao.queryQuotaAccount(userAccountId, quotaAccountType);

        if (Objects.isNull(userAccount)) {
            return null;
        }

        userAccount.setQuotaAccountList(quotaAccountList);
        return userAccount;
    }
}
