package com.project.quotamanagement.quotamanagement.mapper;

import com.project.quotamanagement.quotamanagement.mapper.dos.AccountOrderDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountOrderDOMapper {

    // 通过外部单号查询
    AccountOrderDO getByOutBizNo(String outBizNo);
}