package com.project.quotamanagement.quotamanagement.controller;

import com.project.quotamanagement.quotamanagement.controller.vo.OccupiedQuotaRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.QuotaApplyRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.ReleaseQuotaRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.base.CommonResult;
import com.project.quotamanagement.quotamanagement.service.QuotaCommandService;
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

        CommonResult result = execute(new ControllerTemplate() {
            @Override
            public void check() {

                // 请求校验、鉴权
            }

            @Override
            public void execute() throws Exception {
                quotaCommandService.occupiedQuota(request.getUserAccountId(), request.getQuotaAccountType(), request.getOccupiedQuota());
            }
        });

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

        CommonResult result = execute(new ControllerTemplate() {
            @Override
            public void check() {

                // 请求校验、鉴权
            }

            @Override
            public void execute() throws Exception {
                quotaCommandService.releaseQuota(request.getUserAccountId(), request.getQuotaAccountType(), request.getReleaseQuota());
            }
        });

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
        CommonResult result = execute(new ControllerTemplate() {
            @Override
            public void check() {

                // 请求校验、鉴权
            }

            @Override
            public void execute() throws Exception {
                quotaCommandService.applyQuota(request.getUserAccountId(), request.getQuotaAccountType(), request.getTotalQuota());
            }
        });

        return ResponseEntity.ok(result);
    }
}
