package com.cs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.entity.FacebookAccount;
import com.cs.entity.FacebookAdSet;

/**
 * Facebook 广告组服务接口
 *
 * @author LivePulse
 */
public interface FacebookAdSetService extends IService<FacebookAdSet> {

    /**
     * 同步广告组数据
     *
     * @param account   账户信息
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 同步数量
     */
    int syncAdSets(FacebookAccount account, String startDate, String endDate);
}
