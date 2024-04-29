package com.project.quotamanagement.quotamanagement.controller.vo.base;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BaseRequest {

    /**
     * 操作人
     */
    private String operatorId;
}
