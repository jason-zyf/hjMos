package com.pci.hjMos.common.utils;

import java.time.LocalDateTime;

import com.pci.hjMos.common.constants.RedisKeyConstants;
import com.pci.hjMos.common.enums.PflowTypeEnums;
import com.pci.hjMos.common.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class PflowUtils {
	
	@Autowired
	private RedisProperties redisProperties;
	
	/**
	 * 根据配置文件imc.redis.stationId获得的key
	 * @param type
	 * @param dateTime
	 * @return
	 */
	public String getPflowListKey(PflowTypeEnums type, LocalDateTime dateTime) {
		Assert.hasLength(redisProperties.getStationId(), "imc.redis.stationId is null , check properties");
		return getPflowListKey(type, redisProperties.getStationId(), dateTime);
	}
	
	public String getPflowListKey(PflowTypeEnums type, String stationId, LocalDateTime dateTime) {
		if (PflowTypeEnums.IN.equals(type)) {
			return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_AFC_PFLOW_IN_LIST, stationId, dateTime.toLocalDate());
		} else if (PflowTypeEnums.OUT.equals(type)) {
			return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_AFC_PFLOW_OUT_LIST, stationId, dateTime.toLocalDate());
		} else if (PflowTypeEnums.ALL.equals(type)) {
			return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_AFC_PFLOW_ALL_LIST, stationId, dateTime.toLocalDate());
		}
		return RedisKeyUtils.prefixWithKeyAndDate(null, stationId, dateTime.toLocalDate());
	}

	/**
	 * 获取15分钟打点的实时客流数据
	 * @param type
	 * @param stationId
	 * @param dateTime
	 * @return
	 */
	public String getQuarterPflowListKey(PflowTypeEnums type, String stationId, LocalDateTime dateTime) {
		if (PflowTypeEnums.IN.equals(type)) {
			return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_AFC_PFLOW_QUARTER_IN_LIST, stationId, dateTime.toLocalDate());
		} else if (PflowTypeEnums.OUT.equals(type)) {
			return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_AFC_PFLOW_QUARTER_OUT_LIST, stationId, dateTime.toLocalDate());
		} else if (PflowTypeEnums.ALL.equals(type)) {
			return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_AFC_PFLOW_QUARTER_ALL_LIST, stationId, dateTime.toLocalDate());
		}
		return RedisKeyUtils.prefixWithKeyAndDate(null, stationId, dateTime.toLocalDate());
	}
	
	public String getPrePflowListKey(PflowTypeEnums type, LocalDateTime dateTime) {
		Assert.hasLength(redisProperties.getStationId(), "imc.redis.stationId is null , check properties");
		return getPrePflowListKey(type, redisProperties.getStationId(), dateTime);
	}
	
	public String getPrePflowListKey(PflowTypeEnums type, String stationId, LocalDateTime dateTime) {
		if (PflowTypeEnums.IN.equals(type)) {
			return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_AFC_PRE_PFLOW_IN_LIST, stationId, dateTime.toLocalDate());
		} else if (PflowTypeEnums.OUT.equals(type)) {
			return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_AFC_PRE_PFLOW_OUT_LIST, stationId, dateTime.toLocalDate());
		} else if (PflowTypeEnums.ALL.equals(type)) {
			return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_AFC_PRE_PFLOW_ALL_LIST, stationId, dateTime.toLocalDate());
		}
		return RedisKeyUtils.prefixWithKeyAndDate(null, stationId, dateTime.toLocalDate());
	}
	
	/**
	 * 根据配置文件imc.redis.stationId获得的key
	 * @return
	 */
	public String getPflowStatKey() {
		Assert.hasLength(redisProperties.getStationId(), "imc.redis.stationId is null , check properties");
		return getPflowStatKey(redisProperties.getStationId());
	}
	
	public String getPflowStatKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_AFC_PFLOW_STAT, stationId);
	}

	public String getPflowPeakKey() {
		Assert.hasLength(redisProperties.getStationId(), "imc.redis.stationId is null , check properties");
		return getPflowPeakKey(redisProperties.getStationId());
	}

	public String getPflowPeakKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_AFC_PFLOW_PEAK, stationId);
	}
	
	
	/**
	 * 计算中间结果使用的key
	 * @param stationId
	 * @return
	 */
	public String getStatisticsKey(String stationId) {
		return "pflow-stat:" + stationId;
	}
	
	public String getDetailKey(String stationId) {
		return "pflow-detail:" + stationId;
	}
	/**
	 * 获取AFC人脸闸机的识别信息key
	 * @param stationId
	 * @return
	 */
	public String getAfcFaceCaptureKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_AFC_FACE_CAPTURE_STRING, stationId);
	}
	/**
	 * 获取人脸的sequence
	 * @param stationId
	 * @return
	 */
	public String getAfcFaceCaptureSeqKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_IMC_STREAMING_SEQ, "pflow:" + stationId);
	}
	
	/**
     * 获取人脸的识别总数key
     * @param stationId
     * @param currentTime
     * @return
     */
    public String getAfcFaceCaptureTotalCountKey(String stationId, LocalDateTime currentTime) {
        return RedisKeyUtils.prefixWithKeyAndDate(RedisKeyConstants.PREFIX_AFC_FACE_CAPTURE_TOTAL_COUNT, stationId, currentTime.toLocalDate());
    }
	
	/**
	 * 获取闸机累计客流的key
	 * @param stationId
	 * @return
	 */
	public String getAfcGateTotalFlowKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_AFC_GATE_TOTAL_FLOW, stationId);
	}
	
	/**
	 * 获取闸机实时客流的key
	 * @param stationId
	 * @return
	 */
	public String getAfcGateRtFlowKey(String stationId) {
		return RedisKeyUtils.prefixWithKey(RedisKeyConstants.PREFIX_AFC_GATE_RT_FLOW, stationId);
	}
}
