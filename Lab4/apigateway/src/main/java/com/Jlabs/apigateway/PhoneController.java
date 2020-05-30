package com.Jlabs.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
public class PhoneController {

    @Autowired
    ProxyService _client;

    @Autowired
    ConfigClientAppConfiguration configClientAppConfiguration;

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public ResponseEntity<?> GetConfig() {
        HashMap<String, String> configmap = new HashMap<>();
        configmap.put("Jlabs prop1", configClientAppConfiguration.getProp1());
        configmap.put("Jlabs prop2", configClientAppConfiguration.getProp2());
        configmap.put("Jlabs prop3", configClientAppConfiguration.getProp3());
        configmap.put("Jlabs prop4", configClientAppConfiguration.getProp4());
        return ResponseEntity.ok(configmap);
    }

    @RequestMapping(path = "/phone", method = RequestMethod.GET)
    public ResponseEntity<?> GetAll() {
        try {
            return _client.GetAll();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/phone/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> GetById(@PathVariable Integer id) {
        try {
            return _client.GetById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Phone wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/phone", method = RequestMethod.POST)
    public ResponseEntity<?> Add(@RequestBody Phone phone) throws CustomException {
        try {
            return _client.Add(phone);
        } catch (CustomException e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/phone/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> DeleteById(@PathVariable int id) {
        try {
            return _client.DeleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Phone wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/phone/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> Update(@RequestBody Phone phone, @PathVariable int id) throws CustomException {
        try {
            return _client.Update(id, phone);
        } catch (Exception e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
    }

}
