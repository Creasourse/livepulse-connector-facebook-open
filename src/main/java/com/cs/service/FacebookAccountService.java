package com.cs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.dto.FacebookSyncResultDto;
import com.cs.entity.FacebookAccount;

import java.util.List;

/**
 * Facebook Ads 账户服务接口
 *
 * @author LivePulse
 */
public interface FacebookAccountService extends IService<FacebookAccount> {

    /**
     * 根据 ID 查询账户
     *
     * @param id 账户 ID
     * @return 账户信息
     */
    FacebookAccount findById(Long id);

    /**
     * 根据账户 ID 查询
     *
     * @param accountId Facebook Ads 账户 ID
     * @return 账户信息
     */
    FacebookAccount findByAccountId(String accountId);

    /**
     * 查询所有启用的账户
     *
     * @return 启用的账户列表
     */
    List<FacebookAccount> findEnabled();

    /**
     * 创建账户
     *
     * @param account 账户信息
     * @return 创建后的账户
     */
    FacebookAccount create(FacebookAccount account);

    /**
     * 更新账户
     *
     * @param account 账户信息
     * @return 更新后的账户
     */
    FacebookAccount update(FacebookAccount account);

    /**
     * 删除账户
     *
     * @param id 账户 ID
     */
    void delete(Long id);

    /**
     * 测试连接
     *
     * @param id 账户 ID
     * @return 是否连接成功
     */
    boolean testConnection(Long id);

    /**
     * 手动同步广告系列
     *
     * @param id        账户 ID
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 同步结果
     */
    FacebookSyncResultDto manualSyncCampaigns(Long id, String startDate, String endDate);

    /**
     * 手动同步广告组
     *
     * @param id        账户 ID
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 同步结果
     */
    FacebookSyncResultDto manualSyncAdSets(Long id, String startDate, String endDate);

    /**
     * 手动同步广告
     *
     * @param id        账户 ID
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 同步结果
     */
    FacebookSyncResultDto manualSyncAds(Long id, String startDate, String endDate);

    /**
     * 手动同步洞察数据
     *
     * @param id        账户 ID
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 同步结果
     */
    FacebookSyncResultDto manualSyncInsights(Long id, String startDate, String endDate);
}
