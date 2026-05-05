package com.cs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Facebook Ads 账户实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("facebook_account")
@Schema(name = "FacebookAccount", description = "Facebook Ads 账户配置表")
public class FacebookAccount extends Model<FacebookAccount> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "Facebook Ads 账户 ID")
    @TableField("account_id")
    private String accountId;

    @Schema(description = "账户名称")
    @TableField("account_name")
    private String accountName;

    @Schema(description = "访问令牌")
    @TableField("access_token")
    private String accessToken;

    @Schema(description = "应用 ID")
    @TableField("app_id")
    private String appId;

    @Schema(description = "应用密钥")
    @TableField("app_secret")
    private String appSecret;

    @Schema(description = "Business ID")
    @TableField("business_id")
    private String businessId;

    @Schema(description = "Business 名称")
    @TableField("business_name")
    private String businessName;

    @Schema(description = "账户状态")
    @TableField("account_status")
    private String accountStatus;

    @Schema(description = "货币代码")
    @TableField("currency")
    private String currency;

    @Schema(description = "时区")
    @TableField("timezone")
    private String timezone;

    @Schema(description = "是否启用")
    @TableField("enabled")
    private Boolean enabled;

    @Schema(description = "同步状态")
    @TableField("sync_status")
    private String syncStatus;

    @Schema(description = "最后广告系列同步时间")
    @TableField("last_campaign_sync_time")
    private LocalDateTime lastCampaignSyncTime;

    @Schema(description = "最后广告组同步时间")
    @TableField("last_ad_set_sync_time")
    private LocalDateTime lastAdSetSyncTime;

    @Schema(description = "最后广告同步时间")
    @TableField("last_ad_sync_time")
    private LocalDateTime lastAdSyncTime;

    @Schema(description = "最后洞察数据同步时间")
    @TableField("last_insight_sync_time")
    private LocalDateTime lastInsightSyncTime;

    @Schema(description = "最后错误信息")
    @TableField("last_error_message")
    private String lastErrorMessage;

    @Schema(description = "重试次数")
    @TableField("retry_count")
    private Integer retryCount;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    @TableField("create_by")
    private String createBy;

    @Schema(description = "更新人")
    @TableField("update_by")
    private String updateBy;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
