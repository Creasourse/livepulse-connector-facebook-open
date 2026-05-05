-- Facebook Ads 连接器 PostgreSQL 表结构
-- 版本: 1.8
-- 说明: 同步 Facebook Ads 广告投放数据，支持广告系列、广告组、广告和洞察数据

-- ============================================
-- 清理已存在的表（按依赖关系顺序）
-- ============================================
-- CASCADE 会自动删除所有相关的约束、索引和外键
DROP TABLE IF EXISTS facebook_ad_insight CASCADE;
DROP TABLE IF EXISTS facebook_ad CASCADE;
DROP TABLE IF EXISTS facebook_ad_set CASCADE;
DROP TABLE IF EXISTS facebook_campaign CASCADE;
DROP TABLE IF EXISTS facebook_sync_log CASCADE;
DROP TABLE IF EXISTS facebook_account CASCADE;

-- 清理可能残留的索引
DROP INDEX IF EXISTS idx_insight_account_id CASCADE;
DROP INDEX IF EXISTS idx_insight_campaign_id CASCADE;
DROP INDEX IF EXISTS idx_insight_adset_id CASCADE;
DROP INDEX IF EXISTS idx_insight_ad_id CASCADE;
DROP INDEX IF EXISTS idx_insight_date CASCADE;

DROP INDEX IF EXISTS idx_ad_account_id CASCADE;
DROP INDEX IF EXISTS idx_ad_campaign_id CASCADE;
DROP INDEX IF EXISTS idx_ad_adset_id CASCADE;
DROP INDEX IF EXISTS idx_ad_status CASCADE;
DROP INDEX IF EXISTS idx_ad_created_time CASCADE;

DROP INDEX IF EXISTS idx_adset_account_id CASCADE;
DROP INDEX IF EXISTS idx_adset_campaign_id CASCADE;
DROP INDEX IF EXISTS idx_adset_status CASCADE;
DROP INDEX IF EXISTS idx_adset_start_time CASCADE;

DROP INDEX IF EXISTS idx_campaign_account_id CASCADE;
DROP INDEX IF EXISTS idx_campaign_status CASCADE;
DROP INDEX IF EXISTS idx_campaign_start_time CASCADE;

DROP INDEX IF EXISTS idx_sync_account_id CASCADE;
DROP INDEX IF EXISTS idx_sync_type CASCADE;
DROP INDEX IF EXISTS idx_sync_status CASCADE;
DROP INDEX IF EXISTS idx_sync_start_time CASCADE;

-- ============================================
-- 1. Facebook Ads 账户配置表
-- ============================================
CREATE TABLE facebook_account (
    id BIGSERIAL PRIMARY KEY,
    account_id VARCHAR(255) NOT NULL,
    account_name VARCHAR(500),
    access_token VARCHAR(500) NOT NULL,
    app_id VARCHAR(255),
    app_secret VARCHAR(255),
    business_id VARCHAR(255),
    business_name VARCHAR(500),
    account_status VARCHAR(50),
    currency VARCHAR(10),
    timezone VARCHAR(100),
    enabled BOOLEAN DEFAULT TRUE,
    sync_status VARCHAR(50) DEFAULT 'pending',
    last_campaign_sync_time TIMESTAMP,
    last_ad_set_sync_time TIMESTAMP,
    last_ad_sync_time TIMESTAMP,
    last_insight_sync_time TIMESTAMP,
    last_error_message TEXT,
    retry_count INT DEFAULT 0,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(100),
    update_by VARCHAR(100),
    CONSTRAINT uk_fb_account_id UNIQUE (account_id)
);

-- 表注释
COMMENT ON TABLE facebook_account IS 'Facebook Ads 账户配置表';
COMMENT ON COLUMN facebook_account.account_id IS 'Facebook Ads 账户 ID';
COMMENT ON COLUMN facebook_account.account_name IS '账户名称';
COMMENT ON COLUMN facebook_account.access_token IS '访问令牌';
COMMENT ON COLUMN facebook_account.app_id IS '应用 ID';
COMMENT ON COLUMN facebook_account.app_secret IS '应用密钥';
COMMENT ON COLUMN facebook_account.business_id IS 'Business ID';
COMMENT ON COLUMN facebook_account.business_name IS 'Business 名称';
COMMENT ON COLUMN facebook_account.account_status IS '账户状态: ACTIVE/DISABLED/UNSETTLED/GRACE_PERIOD/PENDING_RISK_REVIEW/TEMPORARY_GRACE_PERIOD';
COMMENT ON COLUMN facebook_account.currency IS '货币代码';
COMMENT ON COLUMN facebook_account.timezone IS '时区';
COMMENT ON COLUMN facebook_account.enabled IS '是否启用';
COMMENT ON COLUMN facebook_account.sync_status IS '同步状态: pending/syncing/success/failed';
COMMENT ON COLUMN facebook_account.last_campaign_sync_time IS '最后广告系列同步时间';
COMMENT ON COLUMN facebook_account.last_ad_set_sync_time IS '最后广告组同步时间';
COMMENT ON COLUMN facebook_account.last_ad_sync_time IS '最后广告同步时间';
COMMENT ON COLUMN facebook_account.last_insight_sync_time IS '最后洞察数据同步时间';
COMMENT ON COLUMN facebook_account.last_error_message IS '最后错误信息';
COMMENT ON COLUMN facebook_account.retry_count IS '重试次数';
COMMENT ON COLUMN facebook_account.create_time IS '创建时间';
COMMENT ON COLUMN facebook_account.update_time IS '更新时间';
COMMENT ON COLUMN facebook_account.create_by IS '创建人';
COMMENT ON COLUMN facebook_account.update_by IS '更新人';

-- ============================================
-- 2. Facebook 广告系列表
-- ============================================
CREATE TABLE facebook_campaign (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    campaign_id BIGINT NOT NULL,
    campaign_name VARCHAR(500),
    objective VARCHAR(100),
    status VARCHAR(50),
    buying_type VARCHAR(100),
    daily_budget DECIMAL(15, 2),
    lifetime_budget DECIMAL(15, 2),
    bid_strategy VARCHAR(100),
    special_ad_categories VARCHAR(500),
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    created_time TIMESTAMP,
    updated_time TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE,
    processed_time TIMESTAMP,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_campaign_account FOREIGN KEY (account_id) REFERENCES facebook_account(id) ON DELETE CASCADE,
    CONSTRAINT uk_fb_campaign_id UNIQUE (account_id, campaign_id)
);

CREATE INDEX idx_campaign_account_id ON facebook_campaign(account_id);
CREATE INDEX idx_campaign_status ON facebook_campaign(status);
CREATE INDEX idx_campaign_start_time ON facebook_campaign(start_time);

-- 表注释
COMMENT ON TABLE facebook_campaign IS 'Facebook 广告系列表';
COMMENT ON COLUMN facebook_campaign.account_id IS '关联的账户 ID';
COMMENT ON COLUMN facebook_campaign.campaign_id IS 'Facebook 广告系列 ID';
COMMENT ON COLUMN facebook_campaign.campaign_name IS '广告系列名称';
COMMENT ON COLUMN facebook_campaign.objective IS '广告目标: AWARENESS/TRAFFIC/ENGAGEMENT/LEADS/APP_PROMOTION/MESSAGES/CONVERSIONS/CATALOG_SALES/STORE_VISITS';
COMMENT ON COLUMN facebook_campaign.status IS '状态: ACTIVE/PAUSED/ARCHIVED/ADSET_PAUSED/CAMPAAGE_PAUSED';
COMMENT ON COLUMN facebook_campaign.buying_type IS '购买类型: AUCTION/RESERVED';
COMMENT ON COLUMN facebook_campaign.daily_budget IS '日预算';
COMMENT ON COLUMN facebook_campaign.lifetime_budget IS '总预算';
COMMENT ON COLUMN facebook_campaign.bid_strategy IS '出价策略: LOWEST_COST_WITHOUT_CAP/LOWEST_COST_WITH_BID_CAP/TARGET_COST/COST_CAP';
COMMENT ON COLUMN facebook_campaign.special_ad_categories IS '特殊广告类别';
COMMENT ON COLUMN facebook_campaign.start_time IS '开始时间';
COMMENT ON COLUMN facebook_campaign.end_time IS '结束时间';
COMMENT ON COLUMN facebook_campaign.created_time IS 'Facebook 创建时间';
COMMENT ON COLUMN facebook_campaign.updated_time IS 'Facebook 更新时间';
COMMENT ON COLUMN facebook_campaign.processed IS '是否已处理';
COMMENT ON COLUMN facebook_campaign.processed_time IS '处理时间';
COMMENT ON COLUMN facebook_campaign.create_time IS '创建时间';
COMMENT ON COLUMN facebook_campaign.update_time IS '更新时间';

-- ============================================
-- 3. Facebook 广告组表
-- ============================================
CREATE TABLE facebook_ad_set (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    campaign_id BIGINT NOT NULL,
    ad_set_id BIGINT NOT NULL,
    ad_set_name VARCHAR(500),
    status VARCHAR(50),
    optimization_goal VARCHAR(100),
    billing_event VARCHAR(100),
    daily_budget DECIMAL(15, 2),
    lifetime_budget DECIMAL(15, 2),
    bid_amount DECIMAL(15, 2),
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    targeting TEXT,
    created_time TIMESTAMP,
    updated_time TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE,
    processed_time TIMESTAMP,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_adset_account FOREIGN KEY (account_id) REFERENCES facebook_account(id) ON DELETE CASCADE,
    CONSTRAINT fk_adset_campaign FOREIGN KEY (campaign_id) REFERENCES facebook_campaign(id) ON DELETE CASCADE,
    CONSTRAINT uk_fb_adset_id UNIQUE (account_id, ad_set_id)
);

CREATE INDEX idx_adset_account_id ON facebook_ad_set(account_id);
CREATE INDEX idx_adset_campaign_id ON facebook_ad_set(campaign_id);
CREATE INDEX idx_adset_status ON facebook_ad_set(status);
CREATE INDEX idx_adset_start_time ON facebook_ad_set(start_time);

-- 表注释
COMMENT ON TABLE facebook_ad_set IS 'Facebook 广告组表';
COMMENT ON COLUMN facebook_ad_set.account_id IS '关联的账户 ID';
COMMENT ON COLUMN facebook_ad_set.campaign_id IS '关联的广告系列 ID';
COMMENT ON COLUMN facebook_ad_set.ad_set_id IS 'Facebook 广告组 ID';
COMMENT ON COLUMN facebook_ad_set.ad_set_name IS '广告组名称';
COMMENT ON COLUMN facebook_ad_set.status IS '状态: ACTIVE/PAUSED/ARCHIVED/ADSET_PAUSED/CAMPAAGE_PAUSED';
COMMENT ON COLUMN facebook_ad_set.optimization_goal IS '优化目标: NONE/REACH/TRAFFIC/IMPRESSIONS/RESPONSES/LEADS';
COMMENT ON COLUMN facebook_ad_set.billing_event IS '计费事件: IMPRESSIONS/CLICKS/VIDEO_VIEWS';
COMMENT ON COLUMN facebook_ad_set.daily_budget IS '日预算';
COMMENT ON COLUMN facebook_ad_set.lifetime_budget IS '总预算';
COMMENT ON COLUMN facebook_ad_set.bid_amount IS '出价金额';
COMMENT ON COLUMN facebook_ad_set.start_time IS '开始时间';
COMMENT ON COLUMN facebook_ad_set.end_time IS '结束时间';
COMMENT ON COLUMN facebook_ad_set.targeting IS '定向条件 (JSON)';
COMMENT ON COLUMN facebook_ad_set.created_time IS 'Facebook 创建时间';
COMMENT ON COLUMN facebook_ad_set.updated_time IS 'Facebook 更新时间';
COMMENT ON COLUMN facebook_ad_set.processed IS '是否已处理';
COMMENT ON COLUMN facebook_ad_set.processed_time IS '处理时间';
COMMENT ON COLUMN facebook_ad_set.create_time IS '创建时间';
COMMENT ON COLUMN facebook_ad_set.update_time IS '更新时间';

-- ============================================
-- 4. Facebook 广告表
-- ============================================
CREATE TABLE facebook_ad (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    campaign_id BIGINT NOT NULL,
    ad_set_id BIGINT NOT NULL,
    ad_id BIGINT NOT NULL,
    ad_name VARCHAR(500),
    status VARCHAR(50),
    type VARCHAR(100),
    creative_type VARCHAR(100),
    thumbnail_url VARCHAR(1000),
    created_time TIMESTAMP,
    updated_time TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE,
    processed_time TIMESTAMP,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ad_account FOREIGN KEY (account_id) REFERENCES facebook_account(id) ON DELETE CASCADE,
    CONSTRAINT fk_ad_campaign FOREIGN KEY (campaign_id) REFERENCES facebook_campaign(id) ON DELETE CASCADE,
    CONSTRAINT fk_ad_adset FOREIGN KEY (ad_set_id) REFERENCES facebook_ad_set(id) ON DELETE CASCADE,
    CONSTRAINT uk_fb_ad_id UNIQUE (account_id, ad_id)
);

CREATE INDEX idx_ad_account_id ON facebook_ad(account_id);
CREATE INDEX idx_ad_campaign_id ON facebook_ad(campaign_id);
CREATE INDEX idx_ad_adset_id ON facebook_ad(ad_set_id);
CREATE INDEX idx_ad_status ON facebook_ad(status);
CREATE INDEX idx_ad_created_time ON facebook_ad(created_time);

-- 表注释
COMMENT ON TABLE facebook_ad IS 'Facebook 广告表';
COMMENT ON COLUMN facebook_ad.account_id IS '关联的账户 ID';
COMMENT ON COLUMN facebook_ad.campaign_id IS '关联的广告系列 ID';
COMMENT ON COLUMN facebook_ad.ad_set_id IS '关联的广告组 ID';
COMMENT ON COLUMN facebook_ad.ad_id IS 'Facebook 广告 ID';
COMMENT ON COLUMN facebook_ad.ad_name IS '广告名称';
COMMENT ON COLUMN facebook_ad.status IS '状态: ACTIVE/PAUSED/ARCHIVED/ADSET_PAUSED/CAMPAAGE_PAUSED/WITH_ISSUES';
COMMENT ON COLUMN facebook_ad.type IS '广告类型';
COMMENT ON COLUMN facebook_ad.creative_type IS '创意类型';
COMMENT ON COLUMN facebook_ad.thumbnail_url IS '缩略图 URL';
COMMENT ON COLUMN facebook_ad.created_time IS 'Facebook 创建时间';
COMMENT ON COLUMN facebook_ad.updated_time IS 'Facebook 更新时间';
COMMENT ON COLUMN facebook_ad.processed IS '是否已处理';
COMMENT ON COLUMN facebook_ad.processed_time IS '处理时间';
COMMENT ON COLUMN facebook_ad.create_time IS '创建时间';
COMMENT ON COLUMN facebook_ad.update_time IS '更新时间';

-- ============================================
-- 5. Facebook 广告洞察数据表
-- ============================================
CREATE TABLE facebook_ad_insight (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    campaign_id BIGINT,
    ad_set_id BIGINT,
    ad_id BIGINT,
    insight_date DATE NOT NULL,
    impressions BIGINT DEFAULT 0,
    clicks BIGINT DEFAULT 0,
    spend DECIMAL(15, 2) DEFAULT 0,
    cpc DECIMAL(15, 2) DEFAULT 0,
    ctr DECIMAL(10, 4) DEFAULT 0,
    reach BIGINT DEFAULT 0,
    frequency DECIMAL(10, 2) DEFAULT 0,
    actions INT DEFAULT 0,
    action_values DECIMAL(15, 2) DEFAULT 0,
    cost_per_action DECIMAL(15, 2) DEFAULT 0,
    conversion_values DECIMAL(15, 2) DEFAULT 0,
    cost_per_conversion DECIMAL(15, 2) DEFAULT 0,
    video_views BIGINT DEFAULT 0,
    video_thruplay_watched_actions BIGINT DEFAULT 0,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_insight_account FOREIGN KEY (account_id) REFERENCES facebook_account(id) ON DELETE CASCADE,
    CONSTRAINT fk_insight_campaign FOREIGN KEY (campaign_id) REFERENCES facebook_campaign(id) ON DELETE CASCADE,
    CONSTRAINT fk_insight_adset FOREIGN KEY (ad_set_id) REFERENCES facebook_ad_set(id) ON DELETE CASCADE,
    CONSTRAINT fk_insight_ad FOREIGN KEY (ad_id) REFERENCES facebook_ad(id) ON DELETE CASCADE,
    CONSTRAINT uk_fb_insight UNIQUE (account_id, campaign_id, ad_set_id, ad_id, insight_date)
);

CREATE INDEX idx_insight_account_id ON facebook_ad_insight(account_id);
CREATE INDEX idx_insight_campaign_id ON facebook_ad_insight(campaign_id);
CREATE INDEX idx_insight_adset_id ON facebook_ad_insight(ad_set_id);
CREATE INDEX idx_insight_ad_id ON facebook_ad_insight(ad_id);
CREATE INDEX idx_insight_date ON facebook_ad_insight(insight_date);

-- 表注释
COMMENT ON TABLE facebook_ad_insight IS 'Facebook 广告洞察数据表';
COMMENT ON COLUMN facebook_ad_insight.account_id IS '关联的账户 ID';
COMMENT ON COLUMN facebook_ad_insight.campaign_id IS '关联的广告系列 ID';
COMMENT ON COLUMN facebook_ad_insight.ad_set_id IS '关联的广告组 ID';
COMMENT ON COLUMN facebook_ad_insight.ad_id IS '关联的广告 ID';
COMMENT ON COLUMN facebook_ad_insight.insight_date IS '数据日期';
COMMENT ON COLUMN facebook_ad_insight.impressions IS '展示次数';
COMMENT ON COLUMN facebook_ad_insight.clicks IS '点击次数';
COMMENT ON COLUMN facebook_ad_insight.spend IS '花费';
COMMENT ON COLUMN facebook_ad_insight.cpc IS '平均点击成本';
COMMENT ON COLUMN facebook_ad_insight.ctr IS '点击率';
COMMENT ON COLUMN facebook_ad_insight.reach IS '触达人数';
COMMENT ON COLUMN facebook_ad_insight.frequency IS '平均频次';
COMMENT ON COLUMN facebook_ad_insight.actions IS '转化次数';
COMMENT ON COLUMN facebook_ad_insight.action_values IS '转化价值';
COMMENT ON COLUMN facebook_ad_insight.cost_per_action IS '单次转化成本';
COMMENT ON COLUMN facebook_ad_insight.conversion_values IS '转化总价值';
COMMENT ON COLUMN facebook_ad_insight.cost_per_conversion IS '单次转化成本';
COMMENT ON COLUMN facebook_ad_insight.video_views IS '视频观看次数';
COMMENT ON COLUMN facebook_ad_insight.video_thruplay_watched_actions IS '视频完播次数';
COMMENT ON COLUMN facebook_ad_insight.created_time IS '创建时间';
COMMENT ON COLUMN facebook_ad_insight.update_time IS '更新时间';

-- ============================================
-- 6. Facebook 同步日志表
-- ============================================
CREATE TABLE facebook_sync_log (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    sync_type VARCHAR(50) NOT NULL,
    sync_method VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    sync_status VARCHAR(50) DEFAULT 'running',
    total_count INT DEFAULT 0,
    success_count INT DEFAULT 0,
    failure_count INT DEFAULT 0,
    error_message TEXT,
    start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_time TIMESTAMP,
    duration BIGINT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_sync_account FOREIGN KEY (account_id) REFERENCES facebook_account(id) ON DELETE CASCADE
);

CREATE INDEX idx_sync_account_id ON facebook_sync_log(account_id);
CREATE INDEX idx_sync_type ON facebook_sync_log(sync_type);
CREATE INDEX idx_sync_status ON facebook_sync_log(sync_status);
CREATE INDEX idx_sync_start_time ON facebook_sync_log(start_time);

-- 表注释
COMMENT ON TABLE facebook_sync_log IS 'Facebook 同步日志表';
COMMENT ON COLUMN facebook_sync_log.account_id IS '关联的账户 ID';
COMMENT ON COLUMN facebook_sync_log.sync_type IS '同步类型: campaign/ad_set/ad/insight/full';
COMMENT ON COLUMN facebook_sync_log.sync_method IS '同步方式: scheduled/manual';
COMMENT ON COLUMN facebook_sync_log.start_date IS '开始日期';
COMMENT ON COLUMN facebook_sync_log.end_date IS '结束日期';
COMMENT ON COLUMN facebook_sync_log.sync_status IS '同步状态: running/success/failed';
COMMENT ON COLUMN facebook_sync_log.total_count IS '总记录数';
COMMENT ON COLUMN facebook_sync_log.success_count IS '成功数量';
COMMENT ON COLUMN facebook_sync_log.failure_count IS '失败数量';
COMMENT ON COLUMN facebook_sync_log.error_message IS '错误信息';
COMMENT ON COLUMN facebook_sync_log.start_time IS '开始时间';
COMMENT ON COLUMN facebook_sync_log.end_time IS '结束时间';
COMMENT ON COLUMN facebook_sync_log.duration IS '耗时（毫秒）';
COMMENT ON COLUMN facebook_sync_log.create_time IS '创建时间';

-- ============================================
-- 初始化数据
-- ============================================

-- 创建示例账户配置（开发环境）
-- INSERT INTO facebook_account (account_id, account_name, access_token, enabled, create_by)
-- VALUES ('act_123456789', 'Demo Account', 'your-access-token-here', TRUE, 'system');
