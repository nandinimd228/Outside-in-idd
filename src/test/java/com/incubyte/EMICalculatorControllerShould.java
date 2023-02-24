package com.incubyte;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EMICalculatorControllerShould {
  EMICalculatorController emiCalculator;
  EMIService emiService = mock(EMIService.class);
  @BeforeEach
  public void init() {
    emiCalculator = new EMICalculatorController(emiService);
  }

  @Test
  @DisplayName("calculate emi")
  public void calculate_emi() {
    int amount = 200000;
    double interestRate = 3.5;
    int durationYears = 30;

    Emi emi = emiCalculator.calculate(amount, interestRate, durationYears);

    verify(emiService).calculate(amount, interestRate, durationYears);
  }

  @Test
  @DisplayName("calculate loan approval")
  public void calculate_loan_approval() {
    int amount = 200000;
    double interestRate = 3.5;
    int durationYears = 30;
    int monthlyIncome = 3000;

    Emi approval = emiCalculator.calculateLoanApproval(amount, interestRate, durationYears, monthlyIncome);

    verify(emiService).calculateLoanApproval(amount, interestRate, durationYears, monthlyIncome);
  }
}
