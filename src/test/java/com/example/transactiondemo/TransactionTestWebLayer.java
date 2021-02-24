package com.example.transactiondemo;

import com.example.transactiondemo.entity.Bike;
import com.example.transactiondemo.repository.CarRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionTestWebLayer {

  @MockBean private CarRepository carRepository;
  @Autowired private TestRestTemplate restTemplate;

  @Test
  @DisplayName("when getting bikes with transactional method then no bikes are returned")
  void whenGettingBikesWithTransactionalMethodThenNoBikesAreReturned() {
    // given
    when(carRepository.save(any())).thenThrow(new RuntimeException());
    // when
    final Bike[] bikes =
        restTemplate.getForEntity(URI.create("/transactional"), Bike[].class).getBody();
    // then
    assertThat(bikes).isEmpty();
  }

  @Test
  @DisplayName("when getting bike without transactional then bikes are returned")
  void whenGettingBikeWithoutTransactionalThenBikesAreReturned() {
    // given
    when(carRepository.save(any())).thenThrow(new RuntimeException());
    // when
    final Bike[] bikes =
        restTemplate.getForEntity(URI.create("/non-transactional"), Bike[].class).getBody();
    // then
    assertThat(bikes).hasSize(2);
  }
}
