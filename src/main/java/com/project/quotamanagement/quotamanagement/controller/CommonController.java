package com.project.quotamanagement.quotamanagement.controller;

import com.project.quotamanagement.quotamanagement.controller.vo.base.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用模板
 */
public abstract class CommonController {

    private static Logger LOGGER = LoggerFactory.getLogger(CommonController.class);


    public void execute(ControllerTemplate template, CommonResult result) {

        // 请求校验、鉴权

        try {

            template.check();

            template.execute();
        } catch (Exception e) {
            result.setErrorMsg(result.getErrorMsg());
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
            LOGGER.error(e.getMessage());
            e.printStackTrace();

            return;
        }


        result.setSuccess(true);
    }
}
