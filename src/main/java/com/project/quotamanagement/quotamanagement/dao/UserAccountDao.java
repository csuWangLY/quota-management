package com.project.quotamanagement.quotamanagement.dao;

import com.project.quotamanagement.quotamanagement.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public interface UserAccountDao {

    UserAccount queryAccount(long userAccountId);

    UserAccount lockAccount(long userAccountId);
}
