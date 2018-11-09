package com.hoau.hoaureport.module.util;
import java.util.UUID;

public class UUIDUtil {
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static long getVersion() {
		return System.currentTimeMillis();
	}
}
