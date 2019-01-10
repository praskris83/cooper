package com.hackthon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aylien.textapi.TextAPIClient;
import com.aylien.textapi.TextAPIException;
import com.aylien.textapi.parameters.SentimentParams;
import com.aylien.textapi.responses.Sentiment;

/**
 * @author vshenbagaraju
 *
 */
@Service
public class SentimentAnalyser {

  private static final Logger LOG = LoggerFactory.getLogger(SentimentAnalyser.class);

  private static final String APP_KEY = "1f040b4eb31d2631edf668528137b073";
  private static final String APP_ID = "33be0324";

  public void getSentimentInfo(String message) throws TextAPIException {

    Sentiment sentiment = getSentiment(message);

  }

  private Sentiment getSentiment(String message) throws TextAPIException {
    TextAPIClient client = new TextAPIClient(APP_ID, APP_KEY);
    SentimentParams.Builder builder = SentimentParams.newBuilder();
    builder.setText(message);
    Sentiment sentiment = client.sentiment(builder.build());
    LOG.info(sentiment.toString());
    return sentiment;
  }

}
