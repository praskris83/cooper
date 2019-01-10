/**
 * 
 */
package com.hackthon.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.aylien.textapi.TextAPIException;
import com.hackthon.bo.ConersationType;
import com.hackthon.bo.Conversation;
import com.hackthon.bo.ConversationContext;
import com.hackthon.bo.SentimentalPolarity;
import com.hackthon.util.CommonUtils;

/**
 * @author prasad
 *
 */
public class PaymentDateHandler {

  @Autowired
  private SentimentAnalyser sentimentAnalyser;


  public String process(String contextKey, String msg) throws IOException {

    String templateKey = "ERROR";

    // Check if Loan Mock Exists -- If not simulate and return Loan Created Template Response -- READY_TO_CHANGE
    // Check if Customer wants to Change the due date -- I can help to change please provide new date -- READY_TO_CHANGE
    // Check if To Date is Present -- If READY_TO_CHANGE || DATE_ERROR - Confirm New date --  CONFIRM_CHANGE
    // Check if To Date Valid -- IF TO Data present NO -- Provide a Valid Date -- DATE_ERROR
    // negativeScore >=3 -- Ask for Callback Date -- CALLBACK_REQUESTED
    // Check if To Date Valid -- IF TO Data present && CALLBACK_REQUESTED NO -- Provide a Valid Date -- DATE_ERROR
    //                        -- Valid Date -- CALLBACK_SHEDULED 
    // negativeScore >=3 -- END Conversation -- EXIT
    // YES 
    // -- Confirm Loan Change -- READY_TO_CHANGE
    // -- CONFIRM_CHANGE -- CHANGE_COMPLETED
    // NO
    // -- ?
    
    return CommonUtils.getTemplate(templateKey);
  }

  public void updateAndCheckNegativeCountFail(ConversationContext context,
      Conversation conversation) {
    try {
      sentimentAnalyser.updateSentimentInfo(conversation);
      updateNegativeScore(context, conversation);
    } catch (TextAPIException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }



  public void updateNegativeScore(ConversationContext context, Conversation conversation) {
    if (SentimentalPolarity.NEGATIVE.equals(conversation.getSentimentalPolarity())) {
      context.setNegativeScore(context.getNegativeScore() > 0 ? context.getNegativeScore() + 1 : 1);
    } else if (SentimentalPolarity.POSITIVE.equals(conversation.getSentimentalPolarity())) {
      context.setNegativeScore(context.getNegativeScore() > 0 ? context.getNegativeScore() - 1 : 0);
    }
  }

}
