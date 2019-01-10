package com.hackthon.util;

import com.hackthon.bo.ConversationContext;
import com.hackthon.bo.ConversationStatus;

/**
 * @author prasad
 *
 */
public class TemplateUtil {
  
  /**
   * 
   * @param context
   * @param status
   * @return
   * 
  INIT("Good Day ! Your TEST Loan is simulated. Payment of $550 towards loan ****${last4Num} is due on ${curPaydate}. Do you want to reschedule this Payment"), 
  READY_TO_CHANGE("Payment of $550 towards loan ****${last4Num} is due on ${curPaydate}. Do you want to reschedule this Payment"), 
  CONFIRM_CHANGE("Your revised next payment date will be ${newPaydate}. Please confirm Yes/NO "), 
  DUE_DATE_ERROR("Please have your revised date within (+) or (-) 5 days of your scheduled payment date ${curPaydate}"), 
  CALLBACK_REQUESTED("We are sorry, we are not able to process your request currently, Please provide a convenient date for our customer care executive to call you back"), 
  CALLBACK_SHEDULED("Thanks and sorry for the bad experience. Our executive will call you back on ${callBackDate}"),
  CHANGE_COMPLETED("You next payment date has been successfully rescheduled from ${curPaydate} to ${newPaydate}, Thanks for contacting us, Good Day"), 
  CALLBACK_DATE_ERROR("Please provide a convenient date for our customer care executive to call you back"),
  EXIT("Customer satisfaction is always a number one priority for us. I'm deeply sorry that that wasn't clearly demonstrated to you. We will have our customer care executive to contact you shortly");
  
   */
  public static String bindTemplate(ConversationContext context, ConversationStatus status){
    switch (status) {
      case INIT:
        return status.getTemplate().replace("${last4Num}", context.getLoanRecord().getLoanId()
            .substring(context.getLoanRecord().getLoanId().length() - 4)).replace("${curPaydate}", context.getLoanRecord().getDate());
      case READY_TO_CHANGE:
        return status.getTemplate().replace("${curPaydate}", context.getLoanRecord().getDate());
      case CONFIRM_CHANGE:
        return status.getTemplate().replace("${newPaydate}", context.getLoanRecord().getPostPondedDueDate());
      case DUE_DATE_ERROR:
        return status.getTemplate().replace("${curPaydate}", context.getLoanRecord().getDate());
      case CALLBACK_SHEDULED:
        return status.getTemplate().replace("${callBackDate}", context.getLoanRecord().getPostPondedDueDate());  
      case CHANGE_COMPLETED:
        return status.getTemplate().replace("${newPaydate}", context.getLoanRecord().getPostPondedDueDate()).replace("${curPaydate}", context.getLoanRecord().getDate());   
      default:
        return status.getTemplate();
    }
  }
}
