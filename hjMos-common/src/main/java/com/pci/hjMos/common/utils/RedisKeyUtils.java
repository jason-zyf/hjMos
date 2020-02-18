package com.pci.hjMos.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.util.Assert;

public final class RedisKeyUtils {

	private static final String SPLITTER = ":";
	
	public static final String prefixWithKey(String prefix, String key) {
		return prefixWithKeyAndDate(prefix, key, null);
	}
	
	public static final String prefixWithKeyAndDate(String prefix, String key, LocalDate date) {
		Assert.hasLength(prefix, "prefix must not be null or empty");
		Assert.hasLength(key, "key must not be null or empty");
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		if (!prefix.endsWith(SPLITTER)) {
			sb.append(SPLITTER);
		}
		sb.append(key);
		if (date != null) {
			if (!key.endsWith(SPLITTER)) {
				sb.append(SPLITTER);
			}
			sb.append(date.format(DateTimeFormatter.BASIC_ISO_DATE));
		}
		return sb.toString();
	}
	
}
