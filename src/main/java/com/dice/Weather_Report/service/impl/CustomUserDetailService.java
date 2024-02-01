package com.dice.Weather_Report.service.impl;

import com.dice.Weather_Report.model.Authority;
import com.dice.Weather_Report.model.Customer;
import com.dice.Weather_Report.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService
{

    @Autowired
    CustomerRepository customerRepository;

    @Override public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException
    {
        Optional<Customer> user = customerRepository.findByEmail(username);
        if (user.isEmpty())
        {
            throw new UsernameNotFoundException("user not found");
        }

        Customer customer = user.get();
        return new User(username, customer.getPwd(), getGrantedAuthorities(customer.getAuthorities()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<Authority> authorities)
    {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Authority authority : authorities)
        {

            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));

        }
        return grantedAuthorities;
    }


}
