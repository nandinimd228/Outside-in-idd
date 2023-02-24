package com.cars;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.List;

@Controller("/")
public class CarController {

  private CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @Get("cars/{id}")
  public CarWrapper getCar(String id) {
    return carService.getCar(id);
  }

  @Get("cars")
  public CarListWrapper getAllCars() {
    return carService.getAllCars();
  }

  @Get("cars/year/{year}")
  public List<Car> getCarsByYear(String year) {
    return carService.getCarsByYear(year);
  }

  @Get("cars/model/{model}")
  public List<Car> getCarsByModel(String model) {
    return carService.getCarsByModel(model);
  }
}
