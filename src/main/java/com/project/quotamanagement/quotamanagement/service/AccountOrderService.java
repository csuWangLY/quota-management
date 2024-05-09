package com.project.quotamanagement.quotamanagement.service;

import com.project.quotamanagement.quotamanagement.model.AccountOrder;

public interface AccountOrderService {

    /**
     * 创建单据
     *
     * @param order 单据
     */
    void createAccountOrder(AccountOrder order) throws Exception;

    /**
     * 幂等判断
     *
     * @param outBizNo 外部单号
     */
    boolean judgeIdempotent(String outBizNo);

}
