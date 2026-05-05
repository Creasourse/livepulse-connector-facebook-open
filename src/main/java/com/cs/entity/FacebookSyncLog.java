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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Facebook 同步日志实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("facebook_sync_log")
@Schema(name = "FacebookSyncLog", description = "Facebook 同步日志表")
public class FacebookSyncLog extends Model<FacebookSyncLog> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联的账户 ID")
    @TableField("account_id")
    private Long accountId;

    @Schema(description = "同步类型")
    @TableField("sync_type")
    private String syncType;

    @Schema(description = "同步方式")
    @TableField("sync_method")
    private String syncMethod;

    @Schema(description = "开始日期")
    @TableField("start_date")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    @TableField("end_date")
    private LocalDate endDate;

    @Schema(description = "同步状态")
    @TableField("sync_status")
    private String syncStatus;

    @Schema(description = "总记录数")
    @TableField("total_count")
    private Integer totalCount;

    @Schema(description = "成功数量")
    @TableField("success_count")
    private Integer successCount;

    @Schema(description = "失败数量")
    @TableField("failure_count")
    private Integer failureCount;

    @Schema(description = "错误信息")
    @TableField("error_message")
    private String errorMessage;

    @Schema(description = "开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @Schema(description = "耗时（毫秒）")
    @TableField("duration")
    private Long duration;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
