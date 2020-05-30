package com.Jlabs.apigateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

@FeignClient(value = "phone-app")
public interface PhoneRepository {
    @RequestMapping(path = "/config", method = RequestMethod.GET)
    ResponseEntity<?> GetConfig();

    @RequestMapping(path="/phone", method = RequestMethod.GET)
    ResponseEntity<?> GetAll();

    @RequestMapping(path="/phone", method = RequestMethod.POST)
    ResponseEntity<?> Add(@RequestBody Phone game) throws CustomException;

    @RequestMapping(path = "/phone/{id}", method = RequestMethod.GET)
    ResponseEntity<?> GetById(@PathVariable Integer id);

    @RequestMapping(path="/phone/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> Update(@PathVariable Integer id,
                             @RequestBody Phone phone) throws ParseException;

    @RequestMapping(path="/phone/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> DeleteById(@PathVariable Integer id);
}
