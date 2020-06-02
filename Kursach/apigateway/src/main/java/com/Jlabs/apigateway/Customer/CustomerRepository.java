package com.Jlabs.ApiGateway.Customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@FeignClient(value="auth-client")
public interface CustomerRepository {

    @PostMapping("/customer/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) throws NoSuchAlgorithmException;

    @PostMapping("/customer/login")
    public ResponseEntity<String> login(@RequestBody Customer customer);

    @GetMapping("/customer")
    public ResponseEntity<String> getAll();

    @GetMapping("/customer/{id}")
    public ResponseEntity<String> getById(@PathVariable int id);

    @GetMapping("/customer/find/{email}")
    public ResponseEntity<String> getIdByEmail(@PathVariable String email);

    @GetMapping("/customer/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestHeader(value = "Authorization") String token);

    @GetMapping("/customer/isClient")
    public ResponseEntity<Boolean> isClient(@RequestHeader(value = "Authorization") String token);
}