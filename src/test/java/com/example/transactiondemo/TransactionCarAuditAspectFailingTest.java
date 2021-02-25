package com.example.transactiondemo;

import com.example.transactiondemo.repository.CarAuditRepository;
import com.example.transactiondemo.service.TheService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionCarAuditAspectFailingTest {
  @MockBean private CarAuditRepository carAuditRepository;
  @Autowired @InjectMocks private TheService service;

  @Test
  @DisplayName(
      "given transactional when aspect fails to write to audit table then transaction rollback")
  void givenTransactionalWhenAspectFailsToWriteToAuditTableThenTransactionIsRollback() {
    // given
    when(carAuditRepository.save(any())).thenThrow(new RuntimeException());
    try {
      service.saveAndDeleteTransactional();
    } catch (Exception ignored) {
    }
    // then
    assertThat(service.getBikes()).isEmpty();
  }

  @Test
  @DisplayName(
      "given non transactional when aspect fails to write to audit table then data is saved inconsistently")
  void givenNonTransactionalWhenAspectFailsToWriteToAuditTableThenDataIsSavedInconsistently() {
    // given
    when(carAuditRepository.save(any())).thenThrow(new RuntimeException());
    try {
      service.saveAndDeleteNonTransactional();
    } catch (Exception ignored) {
    }
    // then
    assertThat(service.getBikes()).isNotEmpty();
  }
}
