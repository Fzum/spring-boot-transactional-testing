package com.example.transactiondemo.service;

import com.example.transactiondemo.entity.Bike;
import com.example.transactiondemo.entity.Car;
import com.example.transactiondemo.repository.BikeRepository;
import com.example.transactiondemo.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TheService {

  private final CarRepository carRepository;
  private final BikeRepository bikeRepository;

  @Transactional
  public void saveCarAndBikeTransactional() {
    saveSomeStuff();
  }

  public void saveCarAndBikeNonTransactional() {
    saveSomeStuff();
  }

  private void saveSomeStuff() {
    bikeRepository.save(new Bike());
    bikeRepository.save(new Bike());

    carRepository.save(new Car());
  }

  public List<Bike> getBikes() {
    return bikeRepository.findAll();
  }
}
