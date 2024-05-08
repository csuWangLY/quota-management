package com.project.quotamanagement.quotamanagement.mapper;

import com.project.quotamanagement.quotamanagement.model.AccountOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountOrderMapper {

    // 查询交易信息通过ID
    AccountOrder getAccountOrderById(@Param("id") Long id);

    // 插入交易信息
    int insertAccountOrder(AccountOrder accountOrder);

    // 更新交易信息
    int updateAccountOrder(AccountOrder accountOrder);

    // 删除交易信息
    int deleteAccountOrderById(@Param("id") Long id);

    // 通过外部单号查询
    AccountOrder getByOutBizNo(@Param("outBizNo") String outBizNo);
}