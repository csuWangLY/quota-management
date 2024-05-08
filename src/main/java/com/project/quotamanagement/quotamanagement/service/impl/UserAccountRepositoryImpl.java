package com.project.quotamanagement.quotamanagement.service.impl;

import com.project.quotamanagement.quotamanagement.mapper.QuotaAccountDOMapper;
import com.project.quotamanagement.quotamanagement.mapper.UserDOMapper;
import com.project.quotamanagement.quotamanagement.mapper.dos.QuotaAccountDO;
import com.project.quotamanagement.quotamanagement.mapper.dos.UserDO;
import com.project.quotamanagement.quotamanagement.model.QuotaAccount;
import com.project.quotamanagement.quotamanagement.model.User;
import com.project.quotamanagement.quotamanagement.service.UserAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class UserAccountRepositoryImpl implements UserAccountRepository {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private QuotaAccountDOMapper quotaAccountDOMapper;

    @Override
    public User userQuery(long userId, String quotaAccountType) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(userId);
        List<QuotaAccountDO> quotaAccountDOList = quotaAccountDOMapper.queryByCondition(userId, quotaAccountType);

        if (Objects.isNull(userDO)) {
            return null;
        }

        User user = new User();
        BeanUtils.copyProperties(userDO, user);

        List<QuotaAccount> quotaAccountList = quotaAccountDOList.stream().map(o -> {
            QuotaAccount quotaAccount = new QuotaAccount();
            BeanUtils.copyProperties(o, quotaAccount);
            return quotaAccount;
        }).collect(Collectors.toList());

        user.setQuotaAccountList(quotaAccountList);
        return user;
    }
}
