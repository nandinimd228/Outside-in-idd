package com.incubyte;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.tree.JsonObject;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
public class CurrencyCalculatorTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  @DisplayName("EMI should be calculated")
  public void emi_should_be_calculated() {
    int amount = 200000;
    double interestRate = 3.5;
    int durationYears = 30;

    Emi emi = httpClient.toBlocking().retrieve(
        "emi-calculator/30?loanAmount=" + amount + "&interestRate="
            + interestRate, Emi.class);
    int interestPaid = emi.getInterestPaid("total_interest_paid");

    Assertions.assertThat(interestPaid).isEqualTo(123312);
  }

  @Test
  @DisplayName("loan should be approved if emi is lesser than half of the monthly income")
  public void loan_should_be_approved_if_emi_is_lesser_than_half_of_the_monthly_income() {
    int amount = 200000;
    double interestRate = 3.5;
    int monthlyIncome = 300;

    Emi emi = httpClient.toBlocking().retrieve(
        "emi-approval/30?loanAmount=" + amount + "&interestRate="
            + interestRate + "&monthlyIncome=" + monthlyIncome , Emi.class);

    boolean loanApproval = emi.getLoanApproval();

    Assertions.assertThat(loanApproval).isFalse();
  }
}
