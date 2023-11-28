package com.luv2code.srpingbootlibrary.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.srpingbootlibrary.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	Payment findByUserEmail(String userEmail);
}
