package com.incubyte;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import jakarta.inject.Singleton;

@Singleton
public class EMIService {

  private EMIClient emiClient;

  public EMIService(EMIClient emiClient) {
    this.emiClient = emiClient;
  }

  public Emi calculate(int amount, double interestRate, int durationYears) {
    return emiClient.fetch(amount, interestRate, durationYears);
  }

  public Emi calculateLoanApproval(int amount, double interestRate, int durationYears,
      int monthlyIncome) {
    Emi response = emiClient.fetch(amount, interestRate, durationYears);

    response.setLoanApproval(response.getMonthlyPayment() * 2 <= monthlyIncome);

    return response;
  }
}
