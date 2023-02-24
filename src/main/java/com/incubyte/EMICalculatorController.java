package com.incubyte;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

@Controller("/")
public class EMICalculatorController {

  private final EMIService emiService;

  public EMICalculatorController(EMIService emiService) {
    this.emiService = emiService;
  }

  @Get("emi-calculator/{durationYears}")
  public Emi calculate(@QueryValue int loanAmount, @QueryValue double interestRate, int durationYears) {
    return emiService.calculate(loanAmount, interestRate, durationYears);
  }

  @Get("emi-approval/{durationYears}")
  public Emi calculateLoanApproval(@QueryValue int loanAmount, @QueryValue double interestRate, int durationYears, @QueryValue int monthlyIncome) {
    return emiService.calculateLoanApproval(loanAmount, interestRate, durationYears, monthlyIncome);
  }
}
