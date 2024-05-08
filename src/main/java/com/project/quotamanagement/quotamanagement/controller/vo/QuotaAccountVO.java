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
     * 租户id
     */
    private String tntInstId;

    /**
     * 创建日期
     */
    private java.sql.Timestamp gmtCreate;

    /**
     * 修改时间
     */
    private java.sql.Timestamp gmtModified;

    /**
     * 账号
     */
    private String accountNo;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 状态
     */
    private String status;

    /**
     * 币种
     */
    private String currency;

    /**
     * 额度上限
     */
    private Long quotaUpperLimit;

    /**
     * 已使用额度
     */
    private Long usedQuota;

    /**
     * 剩余额度
     */
    private Long reserveQuota;
}
