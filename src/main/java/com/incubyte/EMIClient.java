package com.incubyte;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;

@Singleton
@Client("https://mocki.io/v1/")
public interface EMIClient {
  @Get("d17c3974-ae30-4657-b12b-13246ade1c2f")
  Emi fetch(int amount, double interestRate, int durationYears);
}
