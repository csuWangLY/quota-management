package com.project.quotamanagement.quotamanagement.controller.vo;

import com.project.quotamanagement.quotamanagement.controller.vo.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuotaApplyRequest extends BaseRequest {

    /**
     * 用户id
     */
    private long userId;

    /**
     * 额度账户类型
     */
    private String quotaAccountType;

    /**
     * 目标总额度
     */
    private long totalQuota;

    /**
     * 账号
     */
    private String accountNo;
}
