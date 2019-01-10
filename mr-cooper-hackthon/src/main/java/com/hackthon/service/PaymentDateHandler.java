/**
 * 
 */
package com.hackthon.service;

import java.util.List;
import java.util.stream.Collectors;

import com.hackthon.bo.ConersationType;
import com.hackthon.bo.Conversation;
import com.hackthon.bo.ConversationContext;
import com.hackthon.bo.SentimentalPolarity;

/**
 * @author prasad
 *
 */
public class PaymentDateHandler {
  
  public void updateNegativeScore(ConversationContext context) {
    int negativeScore = 0;
    List<Conversation> conversations = context.getConversation();
    conversations = conversations.stream().filter( c -> ConersationType.USER.equals(c.getType()) && SentimentalPolarity.NEGATIVE.equals(c.getSentimentalPolarity())).collect(Collectors.toList());
    
    
  }

}
