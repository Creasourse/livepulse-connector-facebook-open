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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Facebook 广告洞察数据实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("facebook_ad_insight")
@Schema(name = "FacebookAdInsight", description = "Facebook 广告洞察数据表")
public class FacebookAdInsight extends Model<FacebookAdInsight> {

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

    @Schema(description = "关联的广告组 ID")
    @TableField("ad_set_id")
    private Long adSetId;

    @Schema(description = "关联的广告 ID")
    @TableField("ad_id")
    private Long adId;

    @Schema(description = "数据日期")
    @TableField("insight_date")
    private LocalDate insightDate;

    @Schema(description = "展示次数")
    @TableField("impressions")
    private Long impressions;

    @Schema(description = "点击次数")
    @TableField("clicks")
    private Long clicks;

    @Schema(description = "花费")
    @TableField("spend")
    private BigDecimal spend;

    @Schema(description = "平均点击成本")
    @TableField("cpc")
    private BigDecimal cpc;

    @Schema(description = "点击率")
    @TableField("ctr")
    private BigDecimal ctr;

    @Schema(description = "触达人数")
    @TableField("reach")
    private Long reach;

    @Schema(description = "平均频次")
    @TableField("frequency")
    private BigDecimal frequency;

    @Schema(description = "转化次数")
    @TableField("actions")
    private Integer actions;

    @Schema(description = "转化价值")
    @TableField("action_values")
    private BigDecimal actionValues;

    @Schema(description = "单次转化成本")
    @TableField("cost_per_action")
    private BigDecimal costPerAction;

    @Schema(description = "转化总价值")
    @TableField("conversion_values")
    private BigDecimal conversionValues;

    @Schema(description = "单次转化成本")
    @TableField("cost_per_conversion")
    private BigDecimal costPerConversion;

    @Schema(description = "视频观看次数")
    @TableField("video_views")
    private Long videoViews;

    @Schema(description = "视频完播次数")
    @TableField("video_thruplay_watched_actions")
    private Long videoThruplayWatchedActions;

    @Schema(description = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
