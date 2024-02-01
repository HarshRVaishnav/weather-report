package com.dice.Weather_Report.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String name;

    private String email;

    @Column(name = "mobile_nx`umber")
    private String mobileNumber;

    private String pwd;

    @OneToMany(mappedBy = "customer" , fetch=FetchType.EAGER)
    @JsonManagedReference     // in bidirectional parent-child prevent infinite loop while serialising
    private List<Authority> authorities;

}
