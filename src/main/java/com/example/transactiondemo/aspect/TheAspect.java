package com.example.transactiondemo.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TheAspect {

  @Pointcut("execution(* com.example.transactiondemo.repository.CarRepository.deleteById(ID))")
  private void carDeletion() {}

  @After("carDeletion()")
  public void writeToCarHistoryTable() {

  }
}
