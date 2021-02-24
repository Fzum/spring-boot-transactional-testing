package com.example.transactiondemo.controller;

import com.example.transactiondemo.entity.Bike;
import com.example.transactiondemo.service.TheService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TheController {

  private final TheService service;

  @GetMapping("transactional")
  public List<Bike> transactional() {
    try {
      service.saveCarAndBikeTransactional();
    } catch (Exception ignored) {
    }
    return service.getBikes();
  }

  @GetMapping("non-transactional")
  public List<Bike> nonTransactional() {
    try {
      service.saveCarAndBikeNonTransactional();
    } catch (Exception ignored) {
    }
    return service.getBikes();
  }
}
