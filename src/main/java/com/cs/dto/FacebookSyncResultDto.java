package com.cs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Facebook 同步结果 DTO
 *
 * @author LivePulse
 */
@Data
@Builder
@Schema(name = "FacebookSyncResultDto", description = "Facebook 同步结果")
public class FacebookSyncResultDto {

    @Schema(description = "账户 ID")
    private Long accountId;

    @Schema(description = "同步类型: campaign/ad_set/ad/insight/full")
    private String syncType;

    @Schema(description = "同步状态: success/failed")
    private String syncStatus;

    @Schema(description = "开始日期")
    private String startDate;

    @Schema(description = "结束日期")
    private String endDate;

    @Schema(description = "总记录数")
    private Long totalCount;

    @Schema(description = "成功数量")
    private Long successCount;

    @Schema(description = "失败数量")
    private Long failureCount;

    @Schema(description = "耗时（毫秒）")
    private Long duration;

    @Schema(description = "消息")
    private String message;

    @Schema(description = "错误信息")
    private String errorMessage;
}
