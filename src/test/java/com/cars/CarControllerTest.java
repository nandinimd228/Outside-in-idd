package com.cars;

import static org.assertj.core.api.Assertions.assertThat;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
public class CarControllerTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  @DisplayName("return car with id 1")
  public void return_car_with_id_1() {
    CarWrapper carWrapper = httpClient.toBlocking().retrieve("cars/1", CarWrapper.class);
    Car car = carWrapper.getCar();

    assertThat(car.getId()).isEqualTo("1");
    assertThat(car.getCarMake()).isEqualTo("Mitsubishi");
  }

  @Test
  @DisplayName("return list of cars")
  public void return_list_of_cars() {
    CarListWrapper carListWrapper = httpClient.toBlocking().retrieve("cars", CarListWrapper.class);
    List<Car> cars = carListWrapper.getCarList();

    assertThat(cars).hasSize(1000);
  }

  @Test
  @DisplayName("return list of cars with year 2008")
  public void return_list_of_cars_with_year_2008() {
    List<Car> cars =
        httpClient.toBlocking()
            .retrieve(HttpRequest.GET("cars/year/2008"), Argument.listOf(Car.class));

    assertThat(cars)
        .allSatisfy(car ->
            assertThat(car.getYear())
                .isEqualTo("2008"));
  }
  
  @Test
  @DisplayName("return list of cars with model liberty ")
  public void return_list_of_cars_with_model_liberty() {
    List<Car> cars =
        httpClient.toBlocking()
            .retrieve(HttpRequest.GET("cars/model/Liberty"), Argument.listOf(Car.class));

    assertThat(cars)
        .allSatisfy(car ->
            assertThat(car.getModel())
                .isEqualTo("Liberty"));
  }
}
