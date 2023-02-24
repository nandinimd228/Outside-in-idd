package com.cars;

import static org.mockito.Mockito.verify;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarControllerShould {

  CarController carController;
  @Mock
  CarService carService;

  @Test
  @DisplayName("call car service")
  public void call_car_service() {
    carController = new CarController(carService);
    CarWrapper carWrapper = carController.getCar("1");

    verify(carService).getCar("1");
  }

  @Test
  @DisplayName("call car service to get car list")
  public void call_car_service_to_get_car_list() {
    carController = new CarController(carService);
    CarListWrapper carListWrapper = carController.getAllCars();
    verify(carService).getAllCars();
  }

  @Test
  @DisplayName("call car service to get list of cars with year 2008")
  public void call_car_service_to_get_list_of_cars_with_year_2008() {
    carController = new CarController(carService);
    List<Car> cars = carController.getCarsByYear("2008");
    verify(carService).getCarsByYear("2008");
  }

  @Test
  @DisplayName("call car service to get list of cars with model liberty")
  public void call_car_service_to_get_list_of_cars_with_model_liberty() {
    carController = new CarController(carService);
    List<Car> cars = carController.getCarsByModel("Liberty");
    verify(carService).getCarsByModel("Liberty");
  }
}
