package com.Jlabs.AuthClient.Customer;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(path="/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CustomerClient client;
    @Autowired
    private Gson gson;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) throws NoSuchAlgorithmException {
        if (customer.getName() != null && customer.getPassword() != null && customer.getEmail() != null) {
            return new ResponseEntity<String>(client.register(customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Invalid request!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Customer customer) {
        return new ResponseEntity<String>(client.login(customer), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<String>(gson.toJson(repository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable Integer id) {
        return new ResponseEntity<String>(gson.toJson(repository.getById(id)),HttpStatus.OK);
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<String> getByEmail(@PathVariable String email) {
        return new ResponseEntity<String>(gson.toJson(repository.getByEmail(email)),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/isAdmin")
    public ResponseEntity<Boolean> isAdmin() {
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/isClient")
    public ResponseEntity<Boolean> isClient() {
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


}
