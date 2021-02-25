package com.example.transactiondemo.repository;

import com.example.transactiondemo.entity.CarAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarAuditRepository extends JpaRepository<CarAudit, Long> {}
