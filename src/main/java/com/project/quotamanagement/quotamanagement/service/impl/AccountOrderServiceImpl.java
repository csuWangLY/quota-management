package com.project.quotamanagement.quotamanagement.service.impl;

import com.project.quotamanagement.quotamanagement.mapper.AccountOrderDOMapper;
import com.project.quotamanagement.quotamanagement.mapper.dos.AccountOrderDO;
import com.project.quotamanagement.quotamanagement.model.AccountOrder;
import com.project.quotamanagement.quotamanagement.service.AccountOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountOrderServiceImpl implements AccountOrderService {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountOrderServiceImpl.class);

    @Autowired
    private AccountOrderDOMapper accountOrderDOMapper;

    @Override
    public void createAccountOrder(AccountOrder order) throws Exception {
        AccountOrderDO accountOrderDO = new AccountOrderDO();
        BeanUtils.copyProperties(order, accountOrderDO);
        try {
            accountOrderDOMapper.insert(accountOrderDO);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("插入数据幂等");
            throw new Exception("插入数据幂等");
        }
    }

    @Override
    public boolean judgeIdempotent(String outBizNo) {
        AccountOrderDO accountOrder = accountOrderDOMapper.getByOutBizNo(outBizNo);

        return Objects.nonNull(accountOrder);
    }


}
