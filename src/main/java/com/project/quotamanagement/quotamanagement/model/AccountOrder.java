package com.project.quotamanagement.quotamanagement.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data // 自动生成 Getter、Setter、toString、equals 和 hashCode 方法
@NoArgsConstructor // 自动生成无参构造方法
@AllArgsConstructor // 自动生成全参构造方法
public class AccountOrder {
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
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 出款账号
     */
    private String accountNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 交易金额
     */
    private Long tradeAmount;

    /**
     * 状态
     */
    private String status;

    /**
     * 业务单号
     */
    private String bizNo;

    /**
     * 外部单号
     */
    private String outBizNo;

    /**
     * 业务时间
     */
    private Timestamp bizTime;

    /**
     * 业务日期
     */
    private String bizDate;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 币种
     */
    private String currency;
}
