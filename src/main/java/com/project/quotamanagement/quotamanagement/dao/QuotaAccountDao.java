package com.project.quotamanagement.quotamanagement.dao;

import com.project.quotamanagement.quotamanagement.model.QuotaAccount;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuotaAccountDao {

    List<QuotaAccount> queryQuotaAccount(long userAccountId, String quotaAccountType);

    QuotaAccount lockQuotaAccount(long userAccountId, String quotaAccountType);

    long updateQuota(long userAccountId, String quotaAccountType,long totalQuota, long availableQuota, long occupiedQuota);

    long insert(QuotaAccount quotaAccount);
}
