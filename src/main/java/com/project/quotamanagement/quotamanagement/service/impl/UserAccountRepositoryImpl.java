package com.project.quotamanagement.quotamanagement.service.impl;

import com.project.quotamanagement.quotamanagement.mapper.QuotaAccountMapper;
import com.project.quotamanagement.quotamanagement.mapper.UserMapper;
import com.project.quotamanagement.quotamanagement.model.QuotaAccount;
import com.project.quotamanagement.quotamanagement.model.User;
import com.project.quotamanagement.quotamanagement.service.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class UserAccountRepositoryImpl implements UserAccountRepository {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuotaAccountMapper quotaAccountMapper;

    @Override
    public User userQuery(long userId, String quotaAccountType) {
        User userAccount = userMapper.getUserById(userId);
        List<QuotaAccount> quotaAccountList = quotaAccountMapper.queryByCondition(userId, quotaAccountType);

        if (Objects.isNull(userAccount)) {
            return null;
        }

        userAccount.setQuotaAccountList(quotaAccountList);
        return userAccount;
    }
}
