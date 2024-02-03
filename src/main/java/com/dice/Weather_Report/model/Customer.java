package com.dice.Weather_Report.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
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
public class Customer   extends AuditStamp
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String userName;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    private String pwd;

    @OneToMany(mappedBy = "customer" , fetch=FetchType.EAGER)
    @JsonManagedReference     // in bidirectional parent-child prevent infinite loop while serialising
    private List<Authority> authorities;

}
