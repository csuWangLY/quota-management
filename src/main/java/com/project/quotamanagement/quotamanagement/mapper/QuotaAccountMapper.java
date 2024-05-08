package com.project.quotamanagement.quotamanagement.mapper;

import com.project.quotamanagement.quotamanagement.model.QuotaAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuotaAccountMapper {

    // 查询额度账户信息通过ID
    QuotaAccount lockByAccountNo(@Param("accountNo") String accountNo);

    // 查询额度账户信息通过ID
    QuotaAccount getByAccountNo(@Param("accountNo") String accountNo);

    // 查询额度账户信息通过ID
    QuotaAccount getQuotaAccountById(@Param("id") Long id);

    // 插入额度账户信息
    int insertQuotaAccount(QuotaAccount quotaAccount);

    // 更新额度账户信息
    int updateQuotaAccount(QuotaAccount quotaAccount);

    // 删除额度账户信息
    int deleteQuotaAccountById(@Param("id")Long id);

    // 根据条件查询额度账户信息
    List<QuotaAccount> queryByCondition(@Param("userId") Long userId, @Param("quotaAccountType") String quotaAccountType);
}
