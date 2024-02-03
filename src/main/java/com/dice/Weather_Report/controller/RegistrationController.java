package com.dice.Weather_Report.controller;

import com.dice.Weather_Report.dto.RegisterRequest;
import com.dice.Weather_Report.model.Customer;
import com.dice.Weather_Report.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegistrationController
{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/user")
    public ResponseEntity register(@Validated @RequestBody RegisterRequest registerRequest)
    {
        //    @Validated trigger group validation in given record field
        log.info("Start :Registration controller --> register");
        ResponseEntity response;
        Customer customer = Customer.builder()
                .userName(registerRequest.userName())
                .email(registerRequest.email())
                .mobileNumber(registerRequest.mobileNumber())
                .pwd(registerRequest.password())
                .build();

        // Step 1: ENCODE customers password for secured storage
        String encodedPassword = passwordEncoder.encode(customer.getPwd());

        // Step 2: UPDATE customers password with encoded one
        customer.setPwd(encodedPassword);

        //Step 3: SAVE customer with credentials for future authentication
        customerRepository.save(customer);
        log.info("Start :Registration controller --> register--> passed");
        return response = ResponseEntity.status(HttpStatus.CREATED).body("Registration successful ");

    }

}
