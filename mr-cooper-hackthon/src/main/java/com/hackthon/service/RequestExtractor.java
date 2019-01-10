package com.hackthon.service;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.hackthon.bo.Pair;

public class RequestExtractor {

	public static Pair<String, String> extract(String request) throws Exception {
		request = URLDecoder.decode(request, "UTF-8");
		String strArray[] = request.split("&");
		List<String> list = Arrays.asList(strArray);
		list = list.stream().filter(e -> e.contains("Body") || e.contains("From")).collect(Collectors.toList());
		Collections.sort(list);
		String body = list.get(0).split("=")[1];
		String fromNumber = list.get(1).split("=")[1];
		System.out.println("Body=" + body + "\n" + "fromNumber=" + fromNumber);
		return new Pair<String, String>(fromNumber, body);
	}

	// SmsMessageSid=SM440a8de26575dd882d5ac265a4dad944&NumMedia=0&SmsSid=SM440a8de26575dd882d5ac265a4dad944&SmsStatus=received&Body=Init&To=whatsapp%3A%2B14155238886&NumSegments=1&MessageSid=SM440a8de26575dd882d5ac265a4dad944&AccountSid=ACc59453850d8d8e5b78d6e7a8d61f6854&From=whatsapp%3A%2B919791941274&ApiVersion=2010-04-01
}
