package com.pci.hjMos.common.utils;

import com.pci.hjMos.common.constants.RedisKeyConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EscalatorUtils {

    public String getStatDetailKey(String locationId, String escId) {
        return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_ESCALATOR_STAT_DETAIL, locationId + ":" + escId);
    }

    public String getStatDetailKeyPattern(String locationId) {
        return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_ESCALATOR_STAT_DETAIL, locationId + ":*");
    }

    public String getAlarmCountDetailKey(String locationId) {
        return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_ESCALATOR_ALARM_COUNT_DETAIL, locationId);
    }

    public String getAlarmCountTotalKey(String locationId, LocalDateTime curTime) {
        return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_ESCALATOR_ALARM_COUNT_TOTAL, locationId, curTime.toLocalDate());
    }
}
