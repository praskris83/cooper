/**
 * 
 */
package com.hackthon.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylien.textapi.TextAPIException;
import com.hackthon.bo.ApplicationContextHolder;
import com.hackthon.bo.ConersationType;
import com.hackthon.bo.Conversation;
import com.hackthon.bo.ConversationContext;
import com.hackthon.bo.ConversationStatus;
import com.hackthon.bo.LoanRecord;
import com.hackthon.bo.SentimentalPolarity;
import com.hackthon.util.CommonUtils;

/**
 * @author prasad
 *
 */

@Service
public class PaymentDateHandler {

	@Autowired
	private SentimentAnalyser sentimentAnalyser;
	
	@Autowired
	private NLPDateParser nlpDateParser;

	public String process(String contextKey, String msg) throws IOException {
		msg = msg.toLowerCase();
		String templateKey = "ERROR";

		// Check if Loan Mock Exists -- If not simulate and return Loan Created Template
		// Response -- READY_TO_CHANGE
		// Check if Customer wants to Change the due date -- I can help to change please
		// provide new date -- READY_TO_CHANGE
		// Check if To Date is Present -- If READY_TO_CHANGE || DATE_ERROR - Confirm New
		// date -- CONFIRM_CHANGE
		// Check if To Date Valid -- IF TO Data present NO -- Provide a Valid Date --
		// DATE_ERROR
		// negativeScore >=3 -- Ask for Callback Date -- CALLBACK_REQUESTED
		// Check if To Date Valid -- IF TO Data present && CALLBACK_REQUESTED NO --
		// Provide a Valid Date -- DATE_ERROR
		// -- Valid Date -- CALLBACK_SHEDULED
		// negativeScore >=3 -- END Conversation -- EXIT
		// YES
		// -- Confirm Loan Change -- READY_TO_CHANGE
		// -- CONFIRM_CHANGE -- CHANGE_COMPLETED
		// NO
		// -- ?
		ConversationContext context = ApplicationContextHolder.get(contextKey);
		Conversation conversation = new Conversation();
		conversation.setMessage(msg);
		conversation.setType(ConersationType.USER);
		if (context == null) {			
			initConverstationHandleing(contextKey, conversation, context);
		}
		updateAndCheckNegativeCountFail(context, conversation);
		readyToChangeHandler(contextKey, context, conversation);
		findDateInConversationHandler(contextKey, context, conversation);
		confirmDueDateHandler(contextKey, context, conversation);
		return CommonUtils.getTemplate(templateKey);
	}

	private void confirmDueDateHandler(String contextKey, ConversationContext context, Conversation conversation) {
		if (context.getConversationStatus() == ConversationStatus.CONFIRM_CHANGE) {
			if (confirmMsgParser(conversation.getMessage())) {
				updateConversationStatus(context, conversation, ConversationStatus.CHANGE_COMPLETED);
			}
		}

	}

	private boolean confirmMsgParser(String msg) {
		if (msg.contains("yes") || msg.contains("mm") || msg.contains("ok")) {
			return true;
		} else {
			return false;
		}
	}

	private void findDateInConversationHandler(String contextKey, ConversationContext context, Conversation conversation) {
		String msg = conversation.getMessage();
		if (context.getConversationStatus() == ConversationStatus.READY_TO_CHANGE
				|| ConversationStatus.DUE_DATE_ERROR == context.getConversationStatus()) {
			List<LocalDate> dateList = nlpDateParser.getDates(msg);
			LocalDate validDate = CommonUtils.getNewPaymentDate(LocalDate.now(), dateList);
			if (validDate == null) {
				updateConversationStatus(context, conversation, ConversationStatus.DUE_DATE_ERROR);
			} else {
				context.getLoanRecord().setPostPondedDueDate(validDate.toString().replaceAll("-", "/"));
				updateConversationStatus(context, conversation, ConversationStatus.CONFIRM_CHANGE);
			}
		}

	}
	
	private ConversationContext readyToChangeHandler(String contextKey, ConversationContext context, Conversation conversation) {
		if (context.getConversationStatus() == ConversationStatus.INIT && findReadyToChangeStringPattern(conversation.getMessage())) {
			updateConversationStatus(context, conversation, ConversationStatus.READY_TO_CHANGE);
		}
		return context;

	}

	private boolean findReadyToChangeStringPattern(String msg) {
		if (msg.contains("need to change paymentdate") || msg.contains("need to change date")
				|| msg.contains("change date")) {
			return true;
		}
		return false;

	}

	private ConversationContext initConverstationHandleing(String contextKey,
			Conversation conversation, ConversationContext converssationContext) {
		converssationContext = createConversationContext(contextKey);
		ApplicationContextHolder.put(contextKey, converssationContext);
		updateConversationStatus(converssationContext, conversation, ConversationStatus.INIT);
		return converssationContext;
	}

	private void updateConversationStatus(ConversationContext converssationContext,
			Conversation conversation, ConversationStatus conversationStatus) {
		conversation.setEndStatus(conversationStatus);
		converssationContext.setConversationStatus(conversationStatus);

	}

	private ConversationContext createConversationContext(String contextKey) {
		ConversationContext conversationContext = new ConversationContext();
		conversationContext.setLoanRecord(getLoanRecord());
		return conversationContext;
	}

	private LoanRecord getLoanRecord() {
		LoanRecord loanRecord = new LoanRecord();
		String loanId = RandomStringUtils.random(5, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		loanRecord.setLoanId(loanId);
		int loanAmount = RandomUtils.nextInt(1000, 10000);
		loanRecord.setLoanAmount(loanAmount + "");
		loanRecord.setDate(RandomUtils.nextInt(1, 28) + "/" + RandomUtils.nextInt(1, 12) + "/" + "2019");
		loanRecord.setInterestRate(RandomUtils.nextInt(59, 179) + ".5");
		return loanRecord;
	}

	public void updateAndCheckNegativeCountFail(ConversationContext context, Conversation conversation) {
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
