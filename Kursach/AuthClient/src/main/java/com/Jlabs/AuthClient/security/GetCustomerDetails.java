package com.Jlabs.AuthClient.security;

import com.Jlabs.AuthClient.Customer.CustomerRepository;
import com.Jlabs.AuthClient.Customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerDetails implements UserDetailsService {
    @Autowired
    private CustomerRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Customer customer = repository.getByEmail(email);

        if (customer == null) {
            throw new UsernameNotFoundException("Customer '" + email + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(email)//
                .password(customer.getPassword())//
                .authorities(customer.getRole())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
