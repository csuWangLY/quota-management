package com.project.quotamanagement.quotamanagement.service;

import com.project.quotamanagement.quotamanagement.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserAccountRepository {

    User userQuery(long userAccountId, String quotaAccountType);
}
