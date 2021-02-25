package com.example.transactiondemo.aspect;

import com.example.transactiondemo.entity.CarAudit;
import com.example.transactiondemo.repository.CarAuditRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TheAspect {

  private final CarAuditRepository carAuditRepository;

  public TheAspect(CarAuditRepository carAuditRepository) {
    this.carAuditRepository = carAuditRepository;
  }

  @Pointcut("execution(* com.example.transactiondemo.repository.CarRepository.delete(..))")
  private void carDeletion() {}

  @After("carDeletion()")
  public void writeToCarHistoryTable(JoinPoint joinPoint) {
    System.out.println(joinPoint);
    carAuditRepository.save(new CarAudit());
  }
}
