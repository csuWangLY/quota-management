package com.project.quotamanagement.quotamanagement.controller.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 额度账户
 */
@Setter
@Getter
@ToString
public class QuotaAccountVO {

    /**
     * 额度账户类型
     */
    private String quotaAccountType;

    /**
     * 额度账户号码
     */
    private String quotaAccountNo;

    /**
     * 用户id
     */
    private long userAccountId;

    /**
     * 总额度
     */
    private long totalQuota;

    /**
     * 已占用额度
     */
    private long occupiedQuota;

    /**
     * 可用额度
     */
    private long availableQuota;

    /**
     * 生效状态
     */
    private boolean effectiveMode;
}
