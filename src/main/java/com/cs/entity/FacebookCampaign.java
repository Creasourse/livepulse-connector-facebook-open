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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Facebook 广告系列实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("facebook_campaign")
@Schema(name = "FacebookCampaign", description = "Facebook 广告系列表")
public class FacebookCampaign extends Model<FacebookCampaign> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联的账户 ID")
    @TableField("account_id")
    private Long accountId;

    @Schema(description = "Facebook 广告系列 ID")
    @TableField("campaign_id")
    private Long campaignId;

    @Schema(description = "广告系列名称")
    @TableField("campaign_name")
    private String campaignName;

    @Schema(description = "广告目标")
    @TableField("objective")
    private String objective;

    @Schema(description = "状态")
    @TableField("status")
    private String status;

    @Schema(description = "购买类型")
    @TableField("buying_type")
    private String buyingType;

    @Schema(description = "日预算")
    @TableField("daily_budget")
    private BigDecimal dailyBudget;

    @Schema(description = "总预算")
    @TableField("lifetime_budget")
    private BigDecimal lifetimeBudget;

    @Schema(description = "出价策略")
    @TableField("bid_strategy")
    private String bidStrategy;

    @Schema(description = "特殊广告类别")
    @TableField("special_ad_categories")
    private String specialAdCategories;

    @Schema(description = "开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @Schema(description = "Facebook 创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @Schema(description = "Facebook 更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;

    @Schema(description = "是否已处理")
    @TableField("processed")
    private Boolean processed;

    @Schema(description = "处理时间")
    @TableField("processed_time")
    private LocalDateTime processedTime;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
