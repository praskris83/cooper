package com.hackthon.bo;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextHolder {
	static final Map<String, ConversationContext> map;
	static {
		map = new HashMap<>();
	}

	static public void put(String key, ConversationContext conversationContext) {
		map.put(key, conversationContext);
	}

	static public ConversationContext get(String key) {
		return map.get(key);
	}
}
