package com.project.quotamanagement.quotamanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // 自动生成 Getter、Setter、toString、equals 和 hashCode 方法
@NoArgsConstructor // 自动生成无参构造方法
@AllArgsConstructor // 自动生成全参构造方法
public class QuotaAccount {
    /**
     * 自增id
     */
    private Long id;

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
