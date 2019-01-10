package com.hackthon.bo;

import java.util.List;

public class ConversationContext {

  private LoanRecord loanRecord;

  private List<Conversation> conversation;

  private ConversationStatus conversationStatus;

  private int negativeScore;

  private SentimentalPolarity sentimentalPolarity;

  public LoanRecord getLoanRecord() {
    return loanRecord;
  }

  public void setLoanRecord(LoanRecord loanRecord) {
    this.loanRecord = loanRecord;
  }

  public List<Conversation> getConversation() {
    return conversation;
  }

  public void setConversation(List<Conversation> conversation) {
    this.conversation = conversation;
  }

  public ConversationStatus getConversationStatus() {
    return conversationStatus;
  }

  public void setConversationStatus(ConversationStatus conversationStatus) {
    this.conversationStatus = conversationStatus;
  }

  public int getNegativeScore() {
    return negativeScore;
  }

  public void setNegativeScore(int negativeScore) {
    this.negativeScore = negativeScore;
  }

  public SentimentalPolarity getSentimentalPolarity() {
    return sentimentalPolarity;
  }

  public void setSentimentalPolarity(SentimentalPolarity sentimentalPolarity) {
    this.sentimentalPolarity = sentimentalPolarity;
  }

  @Override
  public String toString() {
    return "ConversationContext [loanRecord=" + loanRecord + ", conversation=" + conversation
        + ", conversationStatus=" + conversationStatus + ", SentimentalScore=" + negativeScore
        + ", sentimentalPolarity=" + sentimentalPolarity + "]";
  }

}
