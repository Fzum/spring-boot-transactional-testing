package com.example.transactiondemo;

import com.example.transactiondemo.repository.CarRepository;
import com.example.transactiondemo.service.TheService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionServiceLayerTest {

  @MockBean private CarRepository carRepository;
  @Autowired @InjectMocks private TheService service;

  @Test
  @DisplayName(
      "given transactional when one entity is saved but the other throws error then nothing is saved")
  void givenTransactionalWhenOneEntityIsSavedButTheOtherThrowsErrorThenNothingIsSaved() {
    // given
    when(carRepository.save(any())).thenThrow(new RuntimeException());
    // when
    try {
      service.saveCarAndBikeTransactional();
    } catch (Exception ignored) {
    }
    // then
    assertThat(service.getBikes()).isEmpty();
  }

  @Test
  @DisplayName(
      "given no transaction when one entity is saved but the other throws error then one is saved")
  void givenNoTransactionWhenOneEntityIsSavedButTheOtherThrowsErrorThenOneIsSaved() {
    // given
    when(carRepository.save(any())).thenThrow(new RuntimeException());
    // when
    try {
      service.saveCarAndBikeNonTransactional();
    } catch (Exception ignored) {
    }
    // then
    assertThat(service.getBikes()).isNotEmpty();
  }
}
