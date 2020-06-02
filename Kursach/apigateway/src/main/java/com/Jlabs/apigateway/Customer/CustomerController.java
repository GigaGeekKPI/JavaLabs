package com.Jlabs.ApiGateway.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerRepository repository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) throws NoSuchAlgorithmException {
        return repository.register(customer);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Customer customer) {
        return repository.login(customer);
    }

    @GetMapping("")
    public ResponseEntity<String> getAll(@RequestHeader(value = "Authorization") String token) {
        repository.isAdmin(token);
        return repository.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable int id, @RequestHeader(value = "Authorization") String token) {
        repository.isAdmin(token);
        return repository.getById(id);
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<String> getIdByEmail(@PathVariable String email, @RequestHeader(value = "Authorization") String token) {
        repository.isAdmin(token);
        return repository.getIdByEmail(email);
    }
}