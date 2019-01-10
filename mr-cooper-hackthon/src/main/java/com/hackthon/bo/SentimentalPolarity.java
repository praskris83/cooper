package com.hackthon.bo;

public enum SentimentalPolarity {

  POSITIVE("Positive", 0.5f), NEGATIVE("Negative", 0.75f), NEUTRAL("Negetive", 0f);
  
  private SentimentalPolarity(String value, Float confidenceScoreCutOff) {
    this.value = value;
    this.confidenceScoreCutOff = confidenceScoreCutOff;
  }

  private String value;
  
  private Float confidenceScoreCutOff;
  
  public SentimentalPolarity getSentimentalPolarity(String value) {
    
    for (SentimentalPolarity polarity : SentimentalPolarity.values()) {
      if(polarity.value.equalsIgnoreCase(value)) {
        return polarity;
      }
    }
    return null;
  }
}
