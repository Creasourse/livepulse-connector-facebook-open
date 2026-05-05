package com.cs.scheduled;

import com.cs.config.FacebookConfig;
import com.cs.entity.FacebookAccount;
import com.cs.service.FacebookAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Facebook 数据同步调度任务
 *
 * @author LivePulse
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "facebook.scheduled", name = "enabled", havingValue = "true", matchIfMissing = true)
public class FacebookSyncScheduled {

    private final FacebookAccountService accountService;
    private final FacebookConfig facebookConfig;

    /**
     * 广告系列同步任务
     * 每天凌晨4点执行
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void syncCampaigns() {
        if (!facebookConfig.getScheduled().getCampaignSync().getEnabled()) {
            return;
        }

        log.info("开始执行广告系列同步任务");
        try {
            List<FacebookAccount> accounts = accountService.findEnabled();
            for (FacebookAccount account : accounts) {
                try {
                    // 同步最近30天的广告系列
                    LocalDate endDate = LocalDate.now();
                    LocalDate startDate = endDate.minusDays(30);
                    String startDateStr = startDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
                    String endDateStr = endDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));

                    accountService.manualSyncCampaigns(account.getId(), startDateStr, endDateStr);
                } catch (Exception e) {
                    log.error("账户 {} 广告系列同步失败", account.getAccountName(), e);
                }
            }
        } catch (Exception e) {
            log.error("广告系列同步任务执行失败", e);
        }
        log.info("广告系列同步任务执行完成");
    }

    /**
     * 广告组同步任务
     * 每天凌晨4点执行
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void syncAdSets() {
        if (!facebookConfig.getScheduled().getAdSetSync().getEnabled()) {
            return;
        }

        log.info("开始执行广告组同步任务");
        try {
            List<FacebookAccount> accounts = accountService.findEnabled();
            for (FacebookAccount account : accounts) {
                try {
                    // 同步最近30天的广告组
                    LocalDate endDate = LocalDate.now();
                    LocalDate startDate = endDate.minusDays(30);
                    String startDateStr = startDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
                    String endDateStr = endDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));

                    accountService.manualSyncAdSets(account.getId(), startDateStr, endDateStr);
                } catch (Exception e) {
                    log.error("账户 {} 广告组同步失败", account.getAccountName(), e);
                }
            }
        } catch (Exception e) {
            log.error("广告组同步任务执行失败", e);
        }
        log.info("广告组同步任务执行完成");
    }

    /**
     * 广告同步任务
     * 每天凌晨4点执行
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void syncAds() {
        if (!facebookConfig.getScheduled().getAdSync().getEnabled()) {
            return;
        }

        log.info("开始执行广告同步任务");
        try {
            List<FacebookAccount> accounts = accountService.findEnabled();
            for (FacebookAccount account : accounts) {
                try {
                    // 同步最近30天的广告
                    LocalDate endDate = LocalDate.now();
                    LocalDate startDate = endDate.minusDays(30);
                    String startDateStr = startDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
                    String endDateStr = endDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));

                    accountService.manualSyncAds(account.getId(), startDateStr, endDateStr);
                } catch (Exception e) {
                    log.error("账户 {} 广告同步失败", account.getAccountName(), e);
                }
            }
        } catch (Exception e) {
            log.error("广告同步任务执行失败", e);
        }
        log.info("广告同步任务执行完成");
    }

    /**
     * 洞察数据同步任务
     * 每6小时执行一次
     */
    @Scheduled(fixedDelay = 21600, initialDelay = 240000)
    public void syncInsights() {
        if (!facebookConfig.getScheduled().getInsightSync().getEnabled()) {
            return;
        }

        log.info("开始执行洞察数据同步任务");
        try {
            List<FacebookAccount> accounts = accountService.findEnabled();
            for (FacebookAccount account : accounts) {
                try {
                    // 同步最近7天的洞察数据
                    LocalDate endDate = LocalDate.now();
                    LocalDate startDate = endDate.minusDays(7);
                    String startDateStr = startDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
                    String endDateStr = endDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));

                    accountService.manualSyncInsights(account.getId(), startDateStr, endDateStr);
                } catch (Exception e) {
                    log.error("账户 {} 洞察数据同步失败", account.getAccountName(), e);
                }
            }
        } catch (Exception e) {
            log.error("洞察数据同步任务执行失败", e);
        }
        log.info("洞察数据同步任务执行完成");
    }
}
