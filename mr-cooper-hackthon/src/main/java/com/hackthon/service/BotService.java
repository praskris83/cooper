package com.hackthon.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;
import org.springframework.stereotype.Service;

@Service
public class BotService {

	ThreadLocal<Bot> threadLocalBot = ThreadLocal.withInitial(() -> new Bot("super", getFilePath()));
	ThreadLocal<Chat> threadLocalChat = ThreadLocal.withInitial(() -> {
		Bot bot = threadLocalBot.get();
		bot.brain.nodeStats();
		Chat chat = new Chat(bot);
		return chat;
	});

	public String getBotMessage(String humanMessage) {
		try {
			MagicBooleans.trace_mode = false;
			System.out.println("String human MEssage=" + humanMessage);
			Chat chat = threadLocalChat.get();
			System.out.println("TOPIC=" + chat.predicates.get("topic"));
			String botResponse = chat.multisentenceRespond(humanMessage);
			System.out.println("Bot Response=" + botResponse);
			return botResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}

	}

	private static String getFilePath() {
		Path path = Paths.get(".");
		path = path.normalize().toAbsolutePath();
		String value = path.toString() + File.separator + "src" + File.separator + "main" + File.separator
				+ "resources";
		System.out.println("Path Value=" + value);
		return value;
	}
}
