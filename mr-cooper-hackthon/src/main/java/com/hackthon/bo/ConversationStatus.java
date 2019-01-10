package com.hackthon.bo;

public enum ConversationStatus {

  //Let's Chat 
  INIT("Good Day ! Your TEST Loan is simulated. Payment of $550 towards loan ****${last4Num} is due on ${curPaydate}. Do you want to reschedule this Payment"), 
  READY_TO_CHANGE("Payment of $550 towards loan ****${last4Num} is due on ${curPaydate}. Do you want to reschedule this Payment"), 
  CONFIRM_CHANGE("Your revised next payment date will be ${newPaydate}. Please confirm Yes/NO "), 
  DUE_DATE_ERROR("Please have your revised date within (+) or (-) 5 days of your scheduled payment date"), 
  CALLBACK_REQUESTED("We are sorry, we are not able to process your request currenlty, Please provide a convenient date for our customer care executive to call you back"), 
  CALLBACK_SHEDULED("Thanks and sorry for the bad experience. Our executive will call you back on ${callBackDate}"),
  CHANGE_COMPLETED("You next payment date has been successfully rescheduled from ${curPaydate} to ${newPaydate}, Thanks for contacting us, Good Day"), 
  CALLBACK_DATE_ERROR("Please provide a convenient date for our customer care executive to call you back"),
  EXIT("Customer satisfaction is always a number one priority for us. I'm deeply sorry that that wasn't clearly demonstrated to you. We will have our customer care executive to contact you shortly");
  
  String template;

  private ConversationStatus(String template) {
    this.template = template;
  }

}
