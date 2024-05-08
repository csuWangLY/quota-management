package com.project.quotamanagement.quotamanagement.service;

public interface QuotaCommandService {

    /**
     * 额度申请
     *
     */
    void applyQuota(long userId, String quotaAccountType, long totalQuota, String accountNo) throws Exception;

    /**
     * 额度占用
     */
    void occupiedQuota(long userId, String accountNo, long usedQuota, String outBizNo) throws Exception;

    /**
     * 额度释放
     */
    void releaseQuota(long userId, String accountNo, long restoredQuota, String outBizNo) throws Exception;
}
