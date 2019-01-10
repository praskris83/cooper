package com.hackthon.bo;
public class LoanRecord {

  private String loanAmount;
  private String interestRate;
  private String loanId;
  private String date;
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
  @Override
  public String toString() {
    return "LoanDetails [loanAmount=" + loanAmount + ", interestRate=" + interestRate + ", loanId="
        + loanId + ", date=" + date + "]";
  }

}

