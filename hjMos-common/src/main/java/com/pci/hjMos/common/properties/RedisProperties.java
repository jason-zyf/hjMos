package com.pci.hjMos.common.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "imc.redis")
public class RedisProperties {

	/**
	 * 推送AFC客流数据变化的主题
	 */
	@Deprecated
	private String pushTopic = "topic";
	
	/**
	 * redis内AFC客流数据明细的保存时间(秒)
	 */
	private Long retainTime = 259200l;
	
	/**
	 * 默认处理的stationId
	 */
	private String stationId;
	
	/**
	 * 默认处理的线路id
	 */
	private String lineId;
}
