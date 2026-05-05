package com.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.entity.FacebookSyncLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Facebook 同步日志 Mapper
 *
 * @author LivePulse
 */
@Mapper
public interface FacebookSyncLogMapper extends BaseMapper<FacebookSyncLog> {
}
