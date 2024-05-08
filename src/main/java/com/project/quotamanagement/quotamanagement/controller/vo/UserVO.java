package com.project.quotamanagement.quotamanagement.controller.vo;

import com.project.quotamanagement.quotamanagement.controller.vo.QuotaAccountVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserVO {

    /**
     * 账户id
     */
    private long id;

    /**
     * 账户名
     */
    private String accountName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 额度账户列表
     */
    private List<QuotaAccountVO> quotaAccountVOList;

}
