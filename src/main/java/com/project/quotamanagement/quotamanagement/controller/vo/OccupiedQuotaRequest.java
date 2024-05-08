package com.project.quotamanagement.quotamanagement.controller.vo;

import com.project.quotamanagement.quotamanagement.controller.vo.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OccupiedQuotaRequest extends BaseRequest {

    /**
     * 用户id
     */
    private long userId;

    /**
     * 额度账户类型
     */
    private String accountNo;

    /**
     * 占用额度
     */
    private long occupiedQuota;

    /**
     * 外部id
     */
    private String outBizNo;
}
