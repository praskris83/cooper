package com.hackthon.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BotService {
  @Value("${bots.path}")
  private String botPath;
  ThreadLocal<Bot> threadLocalBot;
  ThreadLocal<Chat> threadLocalChat;

  @PostConstruct
  public void init() {
    threadLocalBot = ThreadLocal.withInitial(() -> new Bot("super", botPath));
    threadLocalChat = ThreadLocal.withInitial(() -> {
      Bot bot = threadLocalBot.get();
      bot.brain.nodeStats();
      Chat chat = new Chat(bot);
      return chat;
    });
  }

  public String getBotMessage(String humanMessage) {
    MagicBooleans.trace_mode = false;
    System.out.println("String human MEssage=" + humanMessage);
    Chat chat = threadLocalChat.get();
    System.out.println("TOPIC=" + chat.predicates.get("topic"));
    String botResponse = chat.multisentenceRespond(humanMessage);
    System.out.println("Bot Response=" + botResponse);
    return botResponse;
  }

  private static String getFilePath() {
    Path path = Paths.get(".");
    path = path.normalize().toAbsolutePath();
    String value = path.toString() + File.separator + "src" + File.separator + "main"
        + File.separator + "resources";
    System.out.println("Path Value=" + value);
    return value;
  }
}
