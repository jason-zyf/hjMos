package com.pci.hjMos.common.utils;

import java.time.LocalDateTime;

import com.pci.hjMos.common.constants.RedisKeyConstants;
import com.pci.hjMos.common.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class PdensityUtils {
	
	@Autowired
	private RedisProperties redisProperties;
	
	public String getDetailKey(String regionId, LocalDateTime dateTime) {
		return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_CCTV_PDENSITY_HIS_LIST, regionId, dateTime.toLocalDate());
	}
	
	public String getPreDetailKey(String regionId, LocalDateTime dateTime) {
		return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_CCTV_PDENSITY_PRE_LIST, regionId, dateTime.toLocalDate());
	}
	
	public String getCurrentKey() {
		Assert.hasLength(redisProperties.getStationId(), "imc.redis.stationId is null , check properties");
		return getCurrentKey(redisProperties.getStationId());
	}
	
	public String getCurrentKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_CCTV_PDENSITY_RT_HASH, stationId);
	}
	
	public String getStatKey() {
		Assert.hasLength(redisProperties.getStationId(), "imc.redis.stationId is null , check properties");
		return getStatKey(redisProperties.getStationId());
	}
	
	public String getStatKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_CCTV_PDENSITY_STAT_HASH, stationId);
	}
	
	public String getCameraFlowKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_CCTV_CAMERA_FLOW_RT_HASH, stationId);
	}
	
	public String getCameraTotalFlowKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_CCTV_CAMERA_TOTALFLOW_RT_HASH, stationId);
	}

	public String getRegionFlowKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_CCTV_PFLOW_STAT_HASH, stationId);
	}

	public String getCameraDensityKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_CCTV_CAMERA_PDENSITY_RT_HASH, stationId);
	}
	
	public String getCameraEventKey(String cameraCode) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_CCTV_ALARM_HASH, cameraCode);
	}
	
	public String getStationStatKey() {
		Assert.hasLength(redisProperties.getStationId(), "imc.redis.stationId is null , check properties");
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_CCTV_STATION_STAT_HASH, redisProperties.getStationId());
	}
	
	public String getRegionTypeStatKey() {
		Assert.hasLength(redisProperties.getStationId(), "imc.redis.stationId is null , check properties");
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_CCTV_REGIONTYPE_STAT_HASH, redisProperties.getStationId());
	}
}
