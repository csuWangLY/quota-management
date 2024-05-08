package com.project.quotamanagement.quotamanagement.mapper;

import com.project.quotamanagement.quotamanagement.mapper.dos.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    // 查询用户信息通过ID
    UserDO lockById(Long id);
}