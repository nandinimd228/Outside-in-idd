package com.cars;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import java.util.List;

@Client("https://myfakeapi.com/api/")
public interface CarClient {

  @Get("cars/{id}")
  CarWrapper fetch(String id);

  @Get("cars")
  CarListWrapper fetchAll();
}
