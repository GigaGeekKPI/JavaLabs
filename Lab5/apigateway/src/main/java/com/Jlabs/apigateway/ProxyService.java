package com.Jlabs.apigateway;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;


@Component
public class ProxyService {
    private static final String BACKEND_A = "phone-app";

    @Autowired
    private PhoneRepository _client;

    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    public ResponseEntity<?> GetAll() {
        return _client.GetAll();
    }

    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    public ResponseEntity<?> GetById(@PathVariable Integer id) {
        return _client.GetById(id);
    }

    @Retry(name = BACKEND_A)
    public ResponseEntity<?> Add(@RequestBody Phone game) throws CustomException {
        try {
            return _client.Add(game);
        } catch (CustomException e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> DeleteById(@PathVariable int id) {
        try {
            return _client.DeleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Phone wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

    @Retry(name = BACKEND_A)
    ResponseEntity<?> Update(@PathVariable int id, @RequestBody Phone phone) {
        try {
            return _client.Update(id, phone);
        } catch (ParseException e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> fallback(Throwable e) {
        return new ResponseEntity<>(new ArrayList<Phone>(),HttpStatus.OK);
    }
}
