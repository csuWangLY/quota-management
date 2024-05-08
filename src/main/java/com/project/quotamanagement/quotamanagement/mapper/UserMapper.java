package com.project.quotamanagement.quotamanagement.mapper;

import com.project.quotamanagement.quotamanagement.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    // 查询用户信息通过ID
    User lockById(@Param("id") Long id);

    // 查询用户信息通过ID
    User getUserById(@Param("id") Long id);

    // 插入用户信息
    int insertUser(User user);

    // 更新用户信息
    int updateUser( User user);

    // 删除用户信息
    int deleteUserById(@Param("id") Long id);
}
