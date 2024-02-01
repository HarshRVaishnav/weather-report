package com.dice.Weather_Report.repository;

import com.dice.Weather_Report.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer , Long>
{

    Optional<Customer> findByEmail(String email);

}
