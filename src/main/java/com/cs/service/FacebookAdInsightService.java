package com.cs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.entity.FacebookAccount;
import com.cs.entity.FacebookAdInsight;

/**
 * Facebook 广告洞察数据服务接口
 *
 * @author LivePulse
 */
public interface FacebookAdInsightService extends IService<FacebookAdInsight> {

    /**
     * 同步洞察数据
     *
     * @param account   账户信息
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 同步数量
     */
    int syncInsights(FacebookAccount account, String startDate, String endDate);
}
