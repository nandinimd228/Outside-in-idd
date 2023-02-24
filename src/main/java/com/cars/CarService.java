package com.cars;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Singleton
public class CarService {

  private CarClient carClient;

  public CarService(CarClient carClient) {
    this.carClient = carClient;
  }

  public CarWrapper getCar(String id) {
    return this.carClient.fetch(id);
  }

  public CarListWrapper getAllCars() {
    return this.carClient.fetchAll();
  }

  public List<Car> getCarsByYear(String year) {
    CarListWrapper carListWrapper = this.carClient.fetchAll();

    return carListWrapper.getCarList().stream().filter(car -> {
      return Objects.equals(car.getYear(), year);
    }).collect(Collectors.toList());
  }

  public List<Car> getCarsByModel(String model) {
    CarListWrapper carListWrapper = this.carClient.fetchAll();

    return carListWrapper.getCarList().stream().filter(car -> {
      return Objects.equals(car.getModel(), model);
    }).collect(Collectors.toList());
  }
}
