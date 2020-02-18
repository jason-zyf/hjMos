package com.pci.hjMos.common.utils;

import com.pci.hjMos.common.constants.RedisKeyConstants;
import com.pci.hjMos.common.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class WifiFlowUtils {
	
	@Autowired
	RedisProperties redisProperties;
	
	public String getTrainKey(String trainCode) {
		Assert.hasLength(trainCode, "trainCode can not be empty or null");
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_WIFI_TRAIN_STAT_HASH, trainCode);
	}
	
	public String getStationKey() {
		return getStationKey(redisProperties.getStationId());
	}
	
	public String getStationKey(String stationId) {
		Assert.hasLength(stationId, "stationId can not be empty or null");
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_WIFI_STATION_STAT_HASH, stationId);
	}
	
}
