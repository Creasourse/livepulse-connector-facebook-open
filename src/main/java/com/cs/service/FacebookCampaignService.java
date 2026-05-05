package com.cs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.entity.FacebookAccount;
import com.cs.entity.FacebookCampaign;

import java.util.List;

/**
 * Facebook 广告系列服务接口
 *
 * @author LivePulse
 */
public interface FacebookCampaignService extends IService<FacebookCampaign> {

    /**
     * 同步广告系列数据
     *
     * @param account   账户信息
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 同步数量
     */
    int syncCampaigns(FacebookAccount account, String startDate, String endDate);
}
