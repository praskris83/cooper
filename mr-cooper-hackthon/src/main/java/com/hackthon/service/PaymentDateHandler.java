/**
 * 
 */
package com.hackthon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.aylien.textapi.TextAPIException;
import com.hackthon.bo.ConersationType;
import com.hackthon.bo.Conversation;
import com.hackthon.bo.ConversationContext;
import com.hackthon.bo.SentimentalPolarity;

/**
 * @author prasad
 *
 */
public class PaymentDateHandler {
  
  @Autowired
  private SentimentAnalyser sentimentAnalyser;
  
  public void updateAndCheckNegativeCountFail(ConversationContext context, Conversation conversation) {  
    try {
      sentimentAnalyser.updateSentimentInfo(conversation);
      updateNegativeScore(context, conversation);
    } catch (TextAPIException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  

  public void updateNegativeScore(ConversationContext context, Conversation conversation) {    
    if(SentimentalPolarity.NEGATIVE.equals(conversation.getSentimentalPolarity())) {
      context.setNegativeScore(context.getNegativeScore() > 0 ? context.getNegativeScore()+1 : 1);      
    } else if(SentimentalPolarity.POSITIVE.equals(conversation.getSentimentalPolarity())) {
      context.setNegativeScore(context.getNegativeScore() > 0 ? context.getNegativeScore()-1 : 0);
    }
  }

}
