package com.cars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarServiceShould {

  CarService carService;

  @Mock
  CarClient carClient;

  @Test
  @DisplayName("call car client")
  public void call_car_client() {
    carService = new CarService(carClient);
    CarWrapper carWrapper = carService.getCar("1");

    verify(carClient).fetch("1");
  }

  @Test
  @DisplayName("call car client to fetch all cars")
  public void call_car_client_to_fetch_all_cars() {
    carService = new CarService(carClient);
    CarListWrapper carListWrapper = carService.getAllCars();

    verify(carClient).fetchAll();
  }

  @Test
  @DisplayName("call car client to fetch all cars and then filter with year ")
  public void call_car_client_to_fetch_all_cars_and_then_filter_with_year() {
    carService = new CarService(carClient);
    CarListWrapper carsByYear = createCars();
    Mockito.when(carClient.fetchAll()).thenReturn(carsByYear);

    List<Car> cars = carService.getCarsByYear("2008");

    assertThat(cars)
        .allSatisfy(car ->
            assertThat(car.getYear())
                .isEqualTo("2008"));
  }

  @Test
  @DisplayName("call car client to fetch all cars and then filter with model ")
  public void call_car_client_to_fetch_all_cars_and_then_filter_with_model() {
    carService = new CarService(carClient);
    CarListWrapper carsByYear = createCars();
    Mockito.when(carClient.fetchAll()).thenReturn(carsByYear);

    List<Car> cars = carService.getCarsByModel("Liberty");

    assertThat(cars)
        .allSatisfy(car ->
            assertThat(car.getModel())
                .isEqualTo("Liberty"));
  }

  private CarListWrapper createCars() {

    Car toyota = new Car("1", "Toyota", "2008", "Liberty");
    Car mahindra = new Car("2", "Mahindra", "2010", "Lumina");
    Car bmw = new Car("3", "BMW", "2008", "Liberty");

    CarListWrapper carListWrapper = new CarListWrapper();

    carListWrapper.addCars(toyota, mahindra, bmw);
    return carListWrapper;
  }
}