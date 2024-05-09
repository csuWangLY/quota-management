package com.project.quotamanagement.quotamanagement.scheduler;

import com.project.quotamanagement.quotamanagement.controller.QuotaManagementCommandController;
import com.project.quotamanagement.quotamanagement.controller.vo.OccupiedQuotaRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.QuotaApplyRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.ReleaseQuotaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync // 2.开启多线程
public class QuotaManagementScheduler {

    @Autowired
    private QuotaManagementCommandController quotaManagementCommandController;

    @Async
    @Scheduled(fixedRate = 50000) // 50秒一次
    public void asyncApply() {
        // 异步任务的具体逻辑
        System.out.println("asyncApply task executed.");

        QuotaApplyRequest request = new QuotaApplyRequest();
        request.setUserId(1);
        request.setQuotaAccountType("TYPE_1");
        request.setTotalQuota(200);
        request.setAccountNo("10001");
        request.setOperatorId("wly");



        quotaManagementCommandController.applyQuota(request);
    }


    @Async
    @Scheduled(cron = "* * * * * ?") //表示每秒
    public void asyncOccupied() {
        // 异步任务的具体逻辑
        System.out.println("asyncOccupied task executed.");

        long current = Timestamp.valueOf(LocalDateTime.now()).getTime();

        OccupiedQuotaRequest request = new OccupiedQuotaRequest();
        request.setUserId(1);
        request.setAccountNo("10001");
        request.setOccupiedQuota(current % 20 + (long) (Math.random() * 10));
        request.setOutBizNo(String.valueOf(current % 20 + (long) (Math.random() * 10)));
        request.setOperatorId("wly");

        quotaManagementCommandController.occupiedQuota(request);

    }

    @Async
    @Scheduled(cron = "* * * * * ?") //表示每秒
    public void asyncRelease() {
        // 异步任务的具体逻辑
        System.out.println("asyncRelease task executed.");
        long current = Timestamp.valueOf(LocalDateTime.now()).getTime();

        ReleaseQuotaRequest request = new ReleaseQuotaRequest();
        request.setUserId(1);
        request.setAccountNo("10001");
        request.setReleaseQuota(current % 20 + (long) (Math.random() * 10));
        request.setOutBizNo(String.valueOf(current % 20 + (long) (Math.random() * 10)));
        request.setOperatorId("wly");

        quotaManagementCommandController.releaseQuota(request);
    }

}
