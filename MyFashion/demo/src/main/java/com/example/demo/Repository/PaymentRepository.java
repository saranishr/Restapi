package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Payment;
@Repository
public interface PaymentRepository extends JpaRepository <Payment,Long> {

}
