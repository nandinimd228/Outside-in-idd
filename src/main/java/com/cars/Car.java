package com.cars;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {

  private String id;
  private String carMake;
  private String model;
  private String year;

  public String getYear() {
    return year;
  }

  public Car(@JsonProperty("id") String id,
      @JsonProperty("car") String carMake,
      @JsonProperty("car_model_year") String year,
      @JsonProperty("car_model") String model) {
    this.id = id;
    this.carMake = carMake;
    this.year = year;
    this.model = model;
  }

  public String getId() {
    return id;
  }

  public String getCarMake() {
    return carMake;
  }

  public String getModel() {
    return model;
  }

}
