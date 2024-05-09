package com.project.quotamanagement.quotamanagement.service.impl;

import com.project.quotamanagement.quotamanagement.mapper.AccountOrderDOMapper;
import com.project.quotamanagement.quotamanagement.mapper.dos.AccountOrderDO;
import com.project.quotamanagement.quotamanagement.model.AccountOrder;
import com.project.quotamanagement.quotamanagement.service.AccountOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountOrderServiceImpl implements AccountOrderService {

    @Autowired
    private AccountOrderDOMapper accountOrderDOMapper;

    @Override
    public void createAccountOrder(AccountOrder order) {
        AccountOrderDO accountOrderDO = new AccountOrderDO();
        BeanUtils.copyProperties(order, accountOrderDO);
        accountOrderDOMapper.insert(accountOrderDO);
    }

    @Override
    public boolean judgeIdempotent(String outBizNo) {
        AccountOrderDO accountOrder = accountOrderDOMapper.getByOutBizNo(outBizNo);

        return Objects.nonNull(accountOrder);
    }


}
