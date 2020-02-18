package com.pci.hjMos.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum CctvEventTypeEnums {

	DENSITY("15", "人员密度预警"),
	ESCALATOR("16", "乘客逆行"),
	INTRUSION("17", "入侵检测"),
	DEPARTURE("18", "离岗检测"),
	ROLLINGD_HBODY("19", "卷帘门人体检测");	
	
	private String code;
	private String desc;
	private CctvEventTypeEnums(String code, String str) {
		this.code = code;
		this.desc = str;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String toDesc() {
		return this.desc;
	}
	
	public static CctvEventTypeEnums of(String val) {
		if (StringUtils.isBlank(val)) {
			return null;
		}
		for (CctvEventTypeEnums e : CctvEventTypeEnums.values()) {
			if (e.getCode().equals(val)) {
				return e;
			}
		}
		return null;
	}
}
