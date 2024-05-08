package com.project.quotamanagement.quotamanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data // 自动生成 Getter、Setter、toString、equals 和 hashCode 方法
@NoArgsConstructor // 自动生成无参构造方法
@AllArgsConstructor // 自动生成全参构造方法
public class User {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 租户id
     */
    private String tntInstId;

    /**
     * 创建日期
     */
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    private Timestamp gmtModified;

    /**
     * 账户名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 账户状态
     */
    private String status;

    /**
     * 额度账户
     */
    private List<QuotaAccount> quotaAccountList;
}
