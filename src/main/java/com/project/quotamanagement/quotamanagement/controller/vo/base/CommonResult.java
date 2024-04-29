package com.project.quotamanagement.quotamanagement.controller.vo.base;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonResult {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 是否可重试
     */
    private boolean retriable;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 错误码
     */
    private String errorCode;

}
