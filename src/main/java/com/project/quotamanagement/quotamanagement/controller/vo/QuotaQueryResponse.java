package com.project.quotamanagement.quotamanagement.controller.vo;

import com.project.quotamanagement.quotamanagement.controller.vo.base.CommonResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuotaQueryResponse extends CommonResult {

    /**
     * 用户账户
     */
    private UserVO userVO;
}
