package com.project.quotamanagement.quotamanagement.mapper;

import com.project.quotamanagement.quotamanagement.mapper.dos.QuotaAccountDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuotaAccountDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuotaAccountDO record);

    int insertSelective(QuotaAccountDO record);

    QuotaAccountDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuotaAccountDO record);

    int updateByPrimaryKey(QuotaAccountDO record);

    // 根据条件查询额度账户信息
    List<QuotaAccountDO> queryByCondition(Long userId, String quotaAccountType);

    // 查询额度账户信息通过ID
    QuotaAccountDO lockByAccountNo(String accountNo);

    // 查询额度账户信息通过ID
    QuotaAccountDO getByAccountNo(String accountNo);
}