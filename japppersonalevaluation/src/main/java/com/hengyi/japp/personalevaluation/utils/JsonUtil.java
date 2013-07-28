package com.hengyi.japp.personalevaluation.utils;

import static com.hengyi.japp.personalevaluation.Constant.JSON;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class JsonUtil {
	public static <E> Iterable<E> convert(String json, Class<E> E)
			throws Exception {
		return convert(JSON.readValue(json, ArrayNode.class), E);
	}

	public static <E> Iterable<E> convert(ArrayNode array, Class<E> E)
			throws Exception {
		List<E> list = Lists.newArrayList();
		for (JsonNode node : array)
			list.add(JSON.readValue(node, E));
		return list;
	}

	public static <E> String convert(Iterable<E> pojos) throws Exception {
		ArrayNode array = JsonNodeFactory.instance.arrayNode();
		for (E pojo : pojos)
			array.addPOJO(pojo);
		return JSON.writeValueAsString(array);
	}

	public static <E> Map<String, Iterable<E>> convertMap(String array,
			Class<E> E) throws Exception {
		return convertMap(JSON.readValue(array, ArrayNode.class), E);
	}

	public static <E> Map<String, Iterable<E>> convertMap(ArrayNode array,
			Class<E> E) throws Exception {
		Map<String, Iterable<E>> result = Maps.newHashMap();
		for (JsonNode node : array) {
			String key = node.get("key").asText();
			ArrayNode valueNode = JSON.readValue(node.get("value"),
					ArrayNode.class);
			result.put(key, Lists.newArrayList(JsonUtil.convert(valueNode, E)));
		}
		return result;
	}

	public static <E> String convert(Map<String, ? extends Iterable<E>> map)
			throws Exception {
		ArrayNode array = JsonNodeFactory.instance.arrayNode();
		for (Entry<String, ? extends Iterable<E>> entry : map.entrySet()) {
			ObjectNode objectNode = JSON.createObjectNode();
			objectNode.put("key", entry.getKey());
			objectNode.put("value", JsonUtil.convert(entry.getValue()));

			array.add(objectNode);
		}
		return JSON.writeValueAsString(array);
	}
}
