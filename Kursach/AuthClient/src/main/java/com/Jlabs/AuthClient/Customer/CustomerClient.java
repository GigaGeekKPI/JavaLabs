package com.Jlabs.AuthClient.Customer;

import com.Jlabs.AuthClient.Error.CustomException;
import com.Jlabs.AuthClient.Role;
import com.Jlabs.AuthClient.security.JwtTokenProvider;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerClient {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    private String customerInfo(Customer customer, String token) {
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        json.addProperty("token", token);
        json.add("customer", gson.toJsonTree(customer));
        return json.toString();
    }

    public String login(Customer u) {
        String username = u.getEmail();
        String password = u.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Customer customer =  repository.getByEmail(username);
            String token = jwtTokenProvider.createToken(username, customer.getRole());
            return customerInfo(customer, token);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String register(Customer customer) {
        if (!customerExists(customer.getEmail(), repository.findAll())) {
            if(customer.getEmail().equals("admin@mail.com") &&
                    customer.getName().equals("admin") &&
                    customer.getPassword().equals("admin")) {
                List<Role> role = new ArrayList<Role>() ;
                customer.setRole(Role.ROLE_ADMIN);
            } else {
                System.out.println("CLIENT");
                customer.setRole(Role.ROLE_CLIENT);
            }
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            repository.save(customer);
            return customerInfo(customer, jwtTokenProvider.createToken(customer.getEmail(), customer.getRole()));
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private boolean customerExists(
            String email, Iterable<Customer> customers) {

        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

}
