package com.hackthon.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackthon.bo.Pair;
import com.hackthon.service.BotService;
import com.hackthon.service.PaymentDateHandler;
import com.hackthon.service.RequestExtractor;

@RestController
public class RequestHandler {
	@Autowired
	private BotService botService;
	@Autowired
	private PaymentDateHandler paymentDateHandler;

	@GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
	public String ping() {
		return "Hello";
	}

	@PostMapping(value = "/handle-msg")
	public String twilloHook(@RequestBody String request) throws Exception {
		/*
		 * Bot bot = new Bot("super", getFilePath()); Chat chat = new Chat(bot);
		 * bot.brain.nodeStats();
		 */
		System.out.println("Request Received=" + request);
		Pair<String, String> pair = RequestExtractor.extract(request);
		System.out.println("PHone Number=" + pair.getLeft());
		System.out.println("Customer Message=" + pair.getRight());
		/* System.out.println(chat.multisentenceRespond(pair.getRight())); */
		//return botService.getBotMessage(pair.getRight());
		return paymentDateHandler.process(pair.getLeft(), pair.getRight());
	}
}
