package com.project.quotamanagement.quotamanagement.controller.vo;

import com.project.quotamanagement.quotamanagement.controller.vo.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 额度查询请求
 */
@Setter
@Getter
@ToString
public class QuotaQueryRequest extends BaseRequest {

    /**
     * 用户id
     */
    private long userId;

    /**
     * 额度账户类型
     */
    private String quotaAccountType;
}
