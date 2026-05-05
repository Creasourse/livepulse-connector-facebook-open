package com.cs.controller;

import com.cs.dto.FacebookSyncResultDto;
import com.cs.entity.FacebookAccount;
import com.cs.service.FacebookAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Facebook Ads 账户控制器
 *
 * @author LivePulse
 */
@Slf4j
@RestController
@RequestMapping("/facebook/account")
@RequiredArgsConstructor
@Tag(name = "Facebook Ads 账户管理", description = "Facebook Ads 账户配置和管理 API")
public class FacebookAccountController {

    private final FacebookAccountService accountService;

    @GetMapping("/{id}")
    @Operation(summary = "获取账户详情", description = "根据 ID 获取账户详细信息")
    public ResponseEntity<FacebookAccount> getById(
            @Parameter(description = "账户 ID") @PathVariable Long id) {
        FacebookAccount account = accountService.findById(id);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @GetMapping("/list")
    @Operation(summary = "获取所有启用的账户", description = "获取所有启用状态的账户列表")
    public ResponseEntity<List<FacebookAccount>> listEnabled() {
        return ResponseEntity.ok(accountService.findEnabled());
    }

    @PostMapping
    @Operation(summary = "创建账户", description = "创建新的 Facebook Ads 账户配置")
    public ResponseEntity<FacebookAccount> create(@RequestBody FacebookAccount account) {
        return ResponseEntity.ok(accountService.create(account));
    }

    @PutMapping
    @Operation(summary = "更新账户", description = "更新账户配置信息")
    public ResponseEntity<FacebookAccount> update(@RequestBody FacebookAccount account) {
        return ResponseEntity.ok(accountService.update(account));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除账户", description = "删除指定的账户配置")
    public ResponseEntity<Void> delete(
            @Parameter(description = "账户 ID") @PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/test")
    @Operation(summary = "测试连接", description = "测试与 Facebook Marketing API 的连接是否正常")
    public ResponseEntity<Boolean> testConnection(
            @Parameter(description = "账户 ID") @PathVariable Long id) {
        return ResponseEntity.ok(accountService.testConnection(id));
    }

    @PostMapping("/{id}/sync/campaigns")
    @Operation(summary = "手动同步广告系列", description = "手动触发广告系列数据同步")
    public ResponseEntity<FacebookSyncResultDto> syncCampaigns(
            @Parameter(description = "账户 ID") @PathVariable Long id,
            @Parameter(description = "开始日期 (yyyyMMdd)") @RequestParam String startDate,
            @Parameter(description = "结束日期 (yyyyMMdd)") @RequestParam String endDate) {
        return ResponseEntity.ok(accountService.manualSyncCampaigns(id, startDate, endDate));
    }

    @PostMapping("/{id}/sync/ad-sets")
    @Operation(summary = "手动同步广告组", description = "手动触发广告组数据同步")
    public ResponseEntity<FacebookSyncResultDto> syncAdSets(
            @Parameter(description = "账户 ID") @PathVariable Long id,
            @Parameter(description = "开始日期 (yyyyMMdd)") @RequestParam String startDate,
            @Parameter(description = "结束日期 (yyyyMMdd)") @RequestParam String endDate) {
        return ResponseEntity.ok(accountService.manualSyncAdSets(id, startDate, endDate));
    }

    @PostMapping("/{id}/sync/ads")
    @Operation(summary = "手动同步广告", description = "手动触发广告数据同步")
    public ResponseEntity<FacebookSyncResultDto> syncAds(
            @Parameter(description = "账户 ID") @PathVariable Long id,
            @Parameter(description = "开始日期 (yyyyMMdd)") @RequestParam String startDate,
            @Parameter(description = "结束日期 (yyyyMMdd)") @RequestParam String endDate) {
        return ResponseEntity.ok(accountService.manualSyncAds(id, startDate, endDate));
    }

    @PostMapping("/{id}/sync/insights")
    @Operation(summary = "手动同步洞察数据", description = "手动触发洞察数据同步")
    public ResponseEntity<FacebookSyncResultDto> syncInsights(
            @Parameter(description = "账户 ID") @PathVariable Long id,
            @Parameter(description = "开始日期 (yyyyMMdd)") @RequestParam String startDate,
            @Parameter(description = "结束日期 (yyyyMMdd)") @RequestParam String endDate) {
        return ResponseEntity.ok(accountService.manualSyncInsights(id, startDate, endDate));
    }
}
