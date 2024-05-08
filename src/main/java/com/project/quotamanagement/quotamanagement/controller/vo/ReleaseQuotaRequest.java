package com.project.quotamanagement.quotamanagement.controller.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReleaseQuotaRequest {
    /**
     * 用户id
     */
    private long userAccountId;

    /**
     * 额度账户类型
     */
    private String accountNo;

    /**
     * 释放额度
     */
    private long releaseQuota;

    /**
     * 外部id
     */
    private String outBizNo;
}
