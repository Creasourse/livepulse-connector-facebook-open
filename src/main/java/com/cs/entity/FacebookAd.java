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
 * Facebook 广告实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("facebook_ad")
@Schema(name = "FacebookAd", description = "Facebook 广告表")
public class FacebookAd extends Model<FacebookAd> {

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

    @Schema(description = "Facebook 广告 ID")
    @TableField("ad_id")
    private Long adId;

    @Schema(description = "广告名称")
    @TableField("ad_name")
    private String adName;

    @Schema(description = "状态")
    @TableField("status")
    private String status;

    @Schema(description = "广告类型")
    @TableField("type")
    private String type;

    @Schema(description = "创意类型")
    @TableField("creative_type")
    private String creativeType;

    @Schema(description = "缩略图 URL")
    @TableField("thumbnail_url")
    private String thumbnailUrl;

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
