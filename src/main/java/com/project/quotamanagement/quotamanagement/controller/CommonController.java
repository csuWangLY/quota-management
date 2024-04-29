package com.project.quotamanagement.quotamanagement.controller;

import com.project.quotamanagement.quotamanagement.controller.vo.base.CommonResult;

/**
 * 通用模板
 */
public abstract class CommonController {


    public CommonResult execute(ControllerTemplate template) {

        CommonResult result = new CommonResult();
        // 请求校验、鉴权

        try {

            template.check();

            template.execute();
        } catch (Exception e) {
            result.setErrorMsg(result.getErrorMsg());
            result.setSuccess(false);
            return result;
        }


        result.setSuccess(true);
        return result;
    }
}
