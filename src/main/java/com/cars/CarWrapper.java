package com.cars;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarWrapper {

  private Car car;

  public CarWrapper(@JsonProperty("Car") Car car) {
    this.car = car;
  }

  public Car getCar() {
    return car;
  }
}
