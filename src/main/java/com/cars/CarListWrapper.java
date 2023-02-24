package com.cars;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CarListWrapper {
  private List<Car> carList = new ArrayList<>();

  public CarListWrapper() {}

  public CarListWrapper(@JsonProperty("cars") List<Car> carList) {
    this.carList = carList;
  }

  public void addCars(Car ... car){
    this.carList.addAll(Arrays.asList(car));
  }
  public List<Car> getCarList() {
    return carList;
  }
}
