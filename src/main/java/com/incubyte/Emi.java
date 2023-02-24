package com.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Emi {

  public int interestPaid;
  private int monthlyPayment;
  private boolean loanApproval;


  public Emi(@JsonProperty("total_interest_paid") int interestPaid,
      @JsonProperty("total") int monthlyPayment) {
    this.interestPaid = interestPaid;
    this.monthlyPayment = monthlyPayment;
  }

  public int getInterestPaid(String total_interest_paid) {
    return interestPaid;
  }

  public int getMonthlyPayment() {
    return monthlyPayment;
  }

  public boolean getLoanApproval() {
    return loanApproval;
  }

  public void setLoanApproval(boolean loanApproval) {
    this.loanApproval = loanApproval;
  }
}
