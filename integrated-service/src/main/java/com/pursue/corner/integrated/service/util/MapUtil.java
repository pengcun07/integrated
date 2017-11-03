package com.pursue.corner.integrated.service.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Map 转换成 Json 字符串
	 * */
	public static <T, U> String toString(Map<T, U> map) throws JsonProcessingException {
		if (map == null || map.size() == 0) {
			return null;
		}
		return objectMapper.writeValueAsString(map);
	}

	/**
	 * Json 字符串是否能转换成 Map 
	 * */
	public static boolean canParse(String mapString) {
		if (StringUtils.isBlank(mapString)) {
			return true;
		}
		try {
			objectMapper.convertValue(mapString, Map.class);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Json 字符串是转换成 Map 如果字符串为空则返回空HashMap对象
	 * */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseString(String mapString) {
		if (StringUtils.isBlank(mapString) || StringUtils.equalsIgnoreCase("null", mapString)) {
			return new HashMap<String, String>();
		}
		try {
			return objectMapper.convertValue(mapString, Map.class);
		} catch (Exception e) {
			throw new RuntimeException("JSON格式Map格式不符", e);
		}
	}

	/**
	 * 判断targets是否是container的子集,包括key和value
	 * */
	public static boolean contains(Map<String, String> container, Map<String, String> targets) {
		if (targets == null || targets.size() == 0) {
			return true;
		} else if (container == null || container.size() == 0) {
			return false;
		} else {
			String key = null;
			for (Iterator<Entry<String, String>> it = targets.entrySet().iterator(); it.hasNext();) {
				Entry<String, String> entry = it.next();
				key = entry.getKey();
				if (!container.containsKey(key) || !StringUtils.equals(entry.getValue(), container.get(key))) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 返回Map中第一个值,str包含key的value,不存在返回null 
	 * */
	public static <T> T firstKeyContain(String str, Map<String, T> map) {
		if (map == null || map.size() == 0) {
			return null;
		}
		for (Iterator<Entry<String, T>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, T> entry = it.next();
			if (StringUtils.contains(str, entry.getKey())) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 返回Map中第一个值
	 * */
	public static <T> T getFirstValue(Map<String, T> map) {
		if (map == null) {
			return null;
		}
		Iterator<Entry<String, T>> iter = map.entrySet().iterator();
		if (iter.hasNext()) {
			return iter.next().getValue();
		}
		return null;
	}

	public static void main(String[] args) throws JsonProcessingException {
		String str = toString(null);
		System.out.println(str);
		System.out.println(parseString(str));
	}
}
