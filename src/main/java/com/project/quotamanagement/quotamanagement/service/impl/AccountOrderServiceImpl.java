package com.project.quotamanagement.quotamanagement.service.impl;

import com.project.quotamanagement.quotamanagement.mapper.AccountOrderMapper;
import com.project.quotamanagement.quotamanagement.model.AccountOrder;
import com.project.quotamanagement.quotamanagement.service.AccountOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountOrderServiceImpl implements AccountOrderService {

    @Autowired
    private AccountOrderMapper accountOrderMapper;

    @Override
    public void createAccountOrder(AccountOrder order) {

    }

    @Override
    public boolean judgeIdempotent(String outBizNo) {
        AccountOrder accountOrder = accountOrderMapper.getByOutBizNo(outBizNo);

        return Objects.nonNull(accountOrder);
    }


}
