package com.hackthon.bo;

import java.time.LocalDate;

public class LoanRecord {

	private String loanAmount;
	private String interestRate;
	private String loanId;
	private String date;
	private String postPondedDueDate;
	private LocalDate payDate;

	public String getPostPondedDueDate() {
		return postPondedDueDate;
	}

	public void setPostPondedDueDate(String postPondedDueDate) {
		this.postPondedDueDate = postPondedDueDate;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

	public LocalDate getPayDate() {
    return payDate;
  }

  public void setPayDate(LocalDate payDate) {
    this.payDate = payDate;
  }

  @Override
	public String toString() {
		return "LoanDetails [loanAmount=" + loanAmount + ", interestRate=" + interestRate + ", loanId=" + loanId
				+ ", date=" + date + "]";
	}

}
