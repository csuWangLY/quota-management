package com.project.quotamanagement.quotamanagement.service;

import com.project.quotamanagement.quotamanagement.model.UserAccount;
import org.springframework.stereotype.Service;

@Service
public interface UserAccountRepository {

    UserAccount accountQuery(long userAccountId, String quotaAccountType);
}
