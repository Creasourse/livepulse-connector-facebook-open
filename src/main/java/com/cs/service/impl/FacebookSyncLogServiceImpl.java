package com.cs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.entity.FacebookSyncLog;
import com.cs.mapper.FacebookSyncLogMapper;
import com.cs.service.FacebookSyncLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Facebook 同步日志服务实现
 *
 * @author LivePulse
 */
@Slf4j
@Service
public class FacebookSyncLogServiceImpl extends ServiceImpl<FacebookSyncLogMapper, FacebookSyncLog> implements FacebookSyncLogService {
}
