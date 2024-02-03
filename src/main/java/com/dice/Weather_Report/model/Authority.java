package com.dice.Weather_Report.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;



@Data
@Entity
public class Authority extends AuditStamp
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @ManyToOne()
    @JsonBackReference
    private Customer customer;

}
