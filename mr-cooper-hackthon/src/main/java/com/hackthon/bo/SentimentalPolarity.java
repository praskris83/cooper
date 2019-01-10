package com.hackthon.bo;

public enum SentimentalPolarity {

  POSITIVE("Positive", 0.5), NEGATIVE("Negative", 0.75), NEUTRAL("Negetive", 0.0);
  
  private SentimentalPolarity(String value, Double confidenceScoreCutOff) {
    this.value = value;
    this.confidenceScoreCutOff = confidenceScoreCutOff;
  }

  private String value;
  
  private Double confidenceScoreCutOff;
  
  public SentimentalPolarity getSentimentalPolarity(String value) {
    
    for (SentimentalPolarity polarity : SentimentalPolarity.values()) {
      if(polarity.value.equalsIgnoreCase(value)) {
        return polarity;
      }
    }
    return null;
  }
}
