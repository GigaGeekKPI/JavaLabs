package com.Jlabs.apigateway.Phone;

import com.Jlabs.apigateway.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@Controller
public class PhoneController {

    @Autowired
    private PhoneRepository repository;

    @RequestMapping(path = "" , method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Phone>> getAll() {
        return repository.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Optional<Phone>> getById(@PathVariable int id) {
        return repository.getById(id);
    }

    @RequestMapping(path="/find/{model}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Phone>> getByModel(@PathVariable String model) {
        return repository.getByModel(model);
    }

    @RequestMapping(path="/new", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> addPhone(@RequestParam(value = "id", required = true) Integer id,
                             @RequestParam(value = "model", required = true) String model,
                             @RequestParam(value = "company", required = true) String company,
                             @RequestParam(value = "purchaser", required = true) String purchaser,
                             @RequestParam(value = "version", required = true) String version,
                             @RequestParam(value = "price", required = true) Float price) {
        return repository.addPhone(id, model, company, purchaser, version, price);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResData<String> deleteCustomer(@PathVariable Integer id) {
        return repository.deletePhone(id);
    }


    @RequestMapping(path="/update", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> update(@RequestParam(value = "model", required = true) String model,
                           @RequestParam(value = "company", required = true) String company,
                           @RequestParam(value = "version", required = true) String version,
                           @RequestParam(value = "price", required = true) Float price) throws ParseException {
        return repository.updatePhone(model, company, version, price);
    }
}