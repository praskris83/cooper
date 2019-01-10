package com.hackthon.bo;

import java.util.List;

public class ConversationContext {

	private LoanRecord loanRecord;
	
	private List<Conversation> conversation;
	
	private ConversationStatus conversationStatus;
	
	private Float SentimentalPolarityScore;
	
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

  public Float getSentimentalPolarityScore() {
    return SentimentalPolarityScore;
  }

  public void setSentimentalPolarityScore(Float sentimentalPolarityScore) {
    SentimentalPolarityScore = sentimentalPolarityScore;
  }

  public SentimentalPolarity getSentimentalPolarity() {
    return sentimentalPolarity;
  }

  public void setSentimentalPolarity(SentimentalPolarity sentimentalPolarity) {
    this.sentimentalPolarity = sentimentalPolarity;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ConversationContext [loanRecord=");
    builder.append(loanRecord);
    builder.append(", conversation=");
    builder.append(conversation);
    builder.append(", conversationStatus=");
    builder.append(conversationStatus);
    builder.append(", SentimentalPolarityScore=");
    builder.append(SentimentalPolarityScore);
    builder.append(", sentimentalPolarity=");
    builder.append(sentimentalPolarity);
    builder.append("]");
    return builder.toString();
  }
	
}
