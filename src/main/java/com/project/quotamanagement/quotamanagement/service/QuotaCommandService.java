package com.project.quotamanagement.quotamanagement.service;

import org.springframework.stereotype.Service;

@Service
public interface QuotaCommandService {

    void applyQuota(long userAccountId, String quotaAccountType, long totalQuota) throws Exception;

    void occupiedQuota(long userAccountId, String quotaAccountType, long usedQuota) throws Exception;

    void releaseQuota(long userAccountId, String quotaAccountType, long restoredQuota) throws Exception;
}
