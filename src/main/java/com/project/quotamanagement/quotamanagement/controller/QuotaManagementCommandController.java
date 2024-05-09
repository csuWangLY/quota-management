package com.project.quotamanagement.quotamanagement.controller;

import com.project.quotamanagement.quotamanagement.controller.vo.OccupiedQuotaRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.QuotaApplyRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.ReleaseQuotaRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.base.CommonResult;
import com.project.quotamanagement.quotamanagement.model.enums.QuotaAccountTypeEnum;
import com.project.quotamanagement.quotamanagement.service.QuotaCommandService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 额度管理操作模块
 */
@Controller
@RequestMapping("/command")
public class QuotaManagementCommandController extends CommonController {

    private static Logger LOGGER = LoggerFactory.getLogger(QuotaManagementCommandController.class);

    @Autowired
    private QuotaCommandService quotaCommandService;

    /**
     * 占用额度
     *
     * @param request 额度占用请求
     * @return 额度占用结果
     */
    @ResponseBody
    @RequestMapping(value = "/occupiedQuota", method = RequestMethod.POST)
    public ResponseEntity<CommonResult> occupiedQuota(OccupiedQuotaRequest request) {

        LOGGER.info("开始额度占用，账号 = {}， 占用额度 = {}", request.getAccountNo(), request.getOccupiedQuota());

        CommonResult result = new CommonResult();

        execute(new ControllerTemplate() {
            @Override
            public void check() throws Exception {
                // 请求校验、鉴权
                if (request.getOccupiedQuota() <= 0 || Strings.isBlank(request.getOutBizNo()) || Strings.isBlank(request.getAccountNo()) || Strings.isBlank(request.getOutBizNo())) {
                    throw new Exception("请求字段不合法");
                }
            }

            @Override
            public void execute() throws Exception {
                quotaCommandService.occupiedQuota(request.getUserId(), request.getAccountNo(), request.getOccupiedQuota(), request.getOutBizNo());
            }
        }, result);

        LOGGER.info("结束额度占用");

        return ResponseEntity.ok(result);
    }

    /**
     * 释放额度
     *
     * @param request 额度占用请求
     * @return 额度占用结果
     */
    @ResponseBody
    @RequestMapping(value = "/releaseQuota", method = RequestMethod.POST)
    public ResponseEntity<CommonResult> releaseQuota(ReleaseQuotaRequest request) {

        LOGGER.info("开始额度释放，账号 = {}， 释放额度 = {}", request.getAccountNo(), request.getReleaseQuota());

        CommonResult result = new CommonResult();

        execute(new ControllerTemplate() {
            @Override
            public void check() throws Exception {
                // 请求校验、鉴权
                if (request.getReleaseQuota() <= 0 || Strings.isBlank(request.getOutBizNo()) || Strings.isBlank(request.getAccountNo()) || Strings.isBlank(request.getOutBizNo())) {
                    throw new Exception("请求字段不合法");
                }
            }

            @Override
            public void execute() throws Exception {
                quotaCommandService.releaseQuota(request.getUserId(), request.getAccountNo(), request.getReleaseQuota(), request.getOutBizNo());
            }
        }, result);

        LOGGER.info("结束额度释放");

        return ResponseEntity.ok(result);
    }

    /**
     * 额度申请、提额、降额
     *
     * @param request 额度申请请求
     * @return 结果
     */
    @ResponseBody
    @RequestMapping(value = "/applyQuota", method = RequestMethod.POST)
    public ResponseEntity<CommonResult> applyQuota(QuotaApplyRequest request) {

        LOGGER.info("开始额度申请，账号 = {}，账户类型 = {}， 申请额度 = {}", request.getAccountNo(), request.getQuotaAccountType(), request.getTotalQuota());

        CommonResult result = new CommonResult();

        execute(new ControllerTemplate() {
            @Override
            public void check() throws Exception {
                // 请求校验、鉴权
                if (request.getTotalQuota() <= 0 || request.getUserId() <= 0 || Strings.isBlank(request.getAccountNo()) || Strings.isBlank(request.getQuotaAccountType()) || QuotaAccountTypeEnum.getByCode(request.getQuotaAccountType()) == null) {
                    throw new Exception("请求字段不合法");
                }
            }

            @Override
            public void execute() throws Exception {
                quotaCommandService.applyQuota(request.getUserId(), request.getQuotaAccountType(), request.getTotalQuota(), request.getAccountNo());
            }
        }, result);

        LOGGER.info("结束额度申请");

        return ResponseEntity.ok(result);
    }
}
