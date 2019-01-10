package com.hackthon.bo;

import java.util.List;

public class Conversation {

  private Long id;

  private String message;

  private SentimentalPolarity sentimentalPolarity;

  private Float sentimentalPolarityScore;

  private boolean reponded;

  private ConversationStatus endStatus;

  private List<Long> responseId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public SentimentalPolarity getSentimentalPolarity() {
    return sentimentalPolarity;
  }

  public void setSentimentalPolarity(SentimentalPolarity sentimentalPolarity) {
    this.sentimentalPolarity = sentimentalPolarity;
  }

  public Float getSentimentalPolarityScore() {
    return sentimentalPolarityScore;
  }

  public void setSentimentalPolarityScore(Float sentimentalPolarityScore) {
    this.sentimentalPolarityScore = sentimentalPolarityScore;
  }

  public boolean isReponded() {
    return reponded;
  }

  public void setReponded(boolean reponded) {
    this.reponded = reponded;
  }

  public List<Long> getResponseId() {
    return responseId;
  }

  public void setResponseId(List<Long> responseId) {
    this.responseId = responseId;
  }


  public ConversationStatus getEndStatus() {
    return endStatus;
  }

  public void setEndStatus(ConversationStatus endStatus) {
    this.endStatus = endStatus;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Conversation [id=");
    builder.append(id);
    builder.append(", message=");
    builder.append(message);
    builder.append(", sentimentalPolarity=");
    builder.append(sentimentalPolarity);
    builder.append(", sentimentalPolarityScore=");
    builder.append(sentimentalPolarityScore);
    builder.append(", reponded=");
    builder.append(reponded);
    builder.append(", endStatus=");
    builder.append(endStatus);
    builder.append(", responseId=");
    builder.append(responseId);
    builder.append("]");
    return builder.toString();
  }


}
