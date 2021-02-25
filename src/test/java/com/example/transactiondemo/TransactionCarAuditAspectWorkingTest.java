package com.example.transactiondemo;

import com.example.transactiondemo.repository.BikeRepository;
import com.example.transactiondemo.repository.CarAuditRepository;
import com.example.transactiondemo.repository.CarRepository;
import com.example.transactiondemo.service.TheService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionCarAuditAspectWorkingTest {
  @Autowired private TheService service;
  @Autowired private BikeRepository bikeRepository;
  @Autowired private CarRepository carRepository;
  @Autowired private CarAuditRepository carAuditRepository;

  @Test
  @DisplayName(
      "given no errors thrown when saving transactional then everything is saved an car is audited")
  void givenNoErrorsThrownWhenSavingTransactionalThenEverythingIsSavedAnCarIsAudited() {
    // when
    service.saveAndDeleteTransactional();
    // then
    assertThat(bikeRepository.count()).isNotZero();
    assertThat(carAuditRepository.count()).isNotZero();
    assertThat(carRepository.count()).isZero();
  }
}
