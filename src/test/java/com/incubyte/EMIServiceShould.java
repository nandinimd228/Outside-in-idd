package com.incubyte;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EMIServiceShould {

  EMIClient emiClient = mock(EMIClient.class);
  EMIService emiService = new EMIService(emiClient);

  @Test
  @DisplayName("verify client")
  public void verify_client() {
    int monthlyIncome = 3000;
    int amount = 200000;
    double interestRate = 3.5;
    int durationYears = 30;

    emiService.calculate(amount, interestRate, durationYears);
    verify(emiClient).fetch(amount, interestRate, durationYears);
  }

  @Test
  @DisplayName("loan should be approved if emi is lesser than half of the monthly income")
  public void loan_should_be_approved_if_emi_is_lesser_than_half_of_the_monthly_income() {
    int monthlyIncome = 3000;
    int amount = 200000;
    double interestRate = 3.5;
    int durationYears = 30;

    Emi emi = new Emi(123312, 898);
    when(emiClient.fetch(amount, interestRate, durationYears)).thenReturn(emi);
    Emi response = emiService.calculateLoanApproval(amount, interestRate, durationYears,
        monthlyIncome);
    assertThat(response.getLoanApproval()).isTrue();
  }

  @Test
  @DisplayName("loan should not be approved if emi is greater than half of the monthly income")
  public void loan_should_not_be_approved_if_emi_is_greater_than_half_of_the_monthly_income() {
    int monthlyIncome = 300;
    int amount = 200000;
    double interestRate = 3.5;
    int durationYears = 30;

    Emi emi = new Emi(123312, 898);
    when(emiClient.fetch(amount, interestRate, durationYears)).thenReturn(emi);
    Emi response = emiService.calculateLoanApproval(amount, interestRate, durationYears,
        monthlyIncome);
    assertThat(response.getLoanApproval()).isFalse();
  }

}