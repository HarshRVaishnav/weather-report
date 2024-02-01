package com.dice.Weather_Report.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Authority  extends AuditStamp
{
           @Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
           private Long id;

          private String name;


          private Customer customer;

}
