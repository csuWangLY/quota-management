package com.project.quotamanagement.quotamanagement.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync // 2.开启多线程
public class QuotaManagementScheduler {

    @Async
    @Scheduled(cron = "* * * * * ?") //表示每秒
    public void asyncTask() {
        // 异步任务的具体逻辑
        System.out.println("Async task executed.");

        // 第一步账户1 消费10 额度
        // 变化为200额度
        // 消费40额度
        // 变化为30额度
    }

}
