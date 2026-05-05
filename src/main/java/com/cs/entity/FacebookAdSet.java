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
 * Facebook 广告组实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("facebook_ad_set")
@Schema(name = "FacebookAdSet", description = "Facebook 广告组表")
public class FacebookAdSet extends Model<FacebookAdSet> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联的账户 ID")
    @TableField("account_id")
    private Long accountId;

    @Schema(description = "关联的广告系列 ID")
    @TableField("campaign_id")
    private Long campaignId;

    @Schema(description = "Facebook 广告组 ID")
    @TableField("ad_set_id")
    private Long adSetId;

    @Schema(description = "广告组名称")
    @TableField("ad_set_name")
    private String adSetName;

    @Schema(description = "状态")
    @TableField("status")
    private String status;

    @Schema(description = "优化目标")
    @TableField("optimization_goal")
    private String optimizationGoal;

    @Schema(description = "计费事件")
    @TableField("billing_event")
    private String billingEvent;

    @Schema(description = "日预算")
    @TableField("daily_budget")
    private BigDecimal dailyBudget;

    @Schema(description = "总预算")
    @TableField("lifetime_budget")
    private BigDecimal lifetimeBudget;

    @Schema(description = "出价金额")
    @TableField("bid_amount")
    private BigDecimal bidAmount;

    @Schema(description = "开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @Schema(description = "定向条件 (JSON)")
    @TableField("targeting")
    private String targeting;

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
