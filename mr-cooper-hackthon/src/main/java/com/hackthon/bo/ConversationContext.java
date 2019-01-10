package com.hackthon.bo;

import java.util.List;

public class ConversationContext {

  private LoanRecord loanRecord;

  private List<Conversation> conversation;

  private ConversationStatus conversationStatus;

  private int negativeScore;

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

  @Override
  public String toString() {
    return "ConversationContext [loanRecord=" + loanRecord + ", conversation=" + conversation
        + ", conversationStatus=" + conversationStatus + ", negativeScore=" + negativeScore + "]";
  }

}
