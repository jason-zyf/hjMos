package com.pci.hjMos.common.utils;

import com.pci.hjMos.common.constants.RedisKeyConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: Donald
 * @date: 2019/7/27
 */
@Component
public class SecurityCheckUtils {

    public String getDeviceStatKey(String stationId) {
        return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_SECURITY_CHECK_DEV_STAT_HASH, stationId);
    }

    public String getRegionStatKey(String stationId, LocalDateTime alarmTime) {
        return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_SECURITY_CHECK_REGION_STAT_HASH, stationId, alarmTime.toLocalDate());
    }

    public String getRegionTotalKey(String stationId, LocalDateTime alarmTime) {
        return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_SECURITY_CHECK_REGION_TOTAL_HASH, stationId, alarmTime.toLocalDate());
    }

    public String getDeviceOnOffKey(String stationId) {
        return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_SECURITY_CHECK_DEV_ON_OFF_HASH, stationId);
    }
    
    public String getEventZsetKey(String stationId) {
    	return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_SECURITY_CHECK_EVENT_ZSET, stationId);
    }
    
    public String getSequenceKey(String stationId) {
    	return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_IMC_STREAMING_SEQ, "securityCheck:" + stationId);
    }
}
