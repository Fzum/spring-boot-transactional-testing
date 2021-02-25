package com.example.transactiondemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Accessors(fluent = true)
public class CarAudit {
  @Id @GeneratedValue private Long id;

  private Long deletedCarId;
}
