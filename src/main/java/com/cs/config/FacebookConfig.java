package com.cs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Facebook Ads 连接器配置
 *
 * @author LivePulse
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "facebook")
public class FacebookConfig {

    /**
     * API 配置
     */
    private Api api = new Api();

    /**
     * 同步配置
     */
    private Sync sync = new Sync();

    /**
     * 调度任务配置
     */
    private Scheduled scheduled = new Scheduled();

    @Data
    public static class Api {
        /**
         * API 版本
         */
        private String version = "v19.0";

        /**
         * 请求超时时间（秒）
         */
        private Integer timeout = 30;

        /**
         * 连接超时时间（秒）
         */
        private Integer connectTimeout = 10;
    }

    @Data
    public static class Sync {
        /**
         * 批次大小
         */
        private Integer batchSize = 100;

        /**
         * 最大重试次数
         */
        private Integer maxRetries = 3;

        /**
         * 重试延迟（毫秒）
         */
        private Long retryDelay = 1000L;

        /**
         * 每次查询的最大天数
         */
        private Integer maxQueryDays = 30;
    }

    @Data
    public static class Scheduled {
        /**
         * 是否启用调度任务
         */
        private Boolean enabled = true;

        /**
         * 广告系列同步配置
         */
        private CampaignSync campaignSync = new CampaignSync();

        /**
         * 广告组同步配置
         */
        private AdSetSync adSetSync = new AdSetSync();

        /**
         * 广告同步配置
         */
        private AdSync adSync = new AdSync();

        /**
         * 洞察数据同步配置
         */
        private InsightSync insightSync = new InsightSync();

        @Data
        public static class CampaignSync {
            private Boolean enabled = true;
            private String cron = "0 0 4 * * ?";
            private Integer initialDelay = 60;
            private Integer fixedDelay = 86400;
        }

        @Data
        public static class AdSetSync {
            private Boolean enabled = true;
            private String cron = "0 0 4 * * ?";
            private Integer initialDelay = 120;
            private Integer fixedDelay = 86400;
        }

        @Data
        public static class AdSync {
            private Boolean enabled = true;
            private String cron = "0 0 4 * * ?";
            private Integer initialDelay = 180;
            private Integer fixedDelay = 86400;
        }

        @Data
        public static class InsightSync {
            private Boolean enabled = true;
            private String cron = "0 0 */6 * * ?";
            private Integer initialDelay = 240;
            private Integer fixedDelay = 21600;
        }
    }
}
