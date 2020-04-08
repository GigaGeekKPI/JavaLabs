package com.Jlabs.EurekaClient.Phone;

import com.Jlabs.EurekaClient.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path="/phone")
public class PhoneController {

    @Autowired
    private PhoneRepository repository;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Phone>> getAll() {
        return new ResData<Iterable<Phone>>(repository.findAll(), instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody ResData<Optional<Phone>> getById(@PathVariable int id) {
        return new ResData<Optional<Phone>>(repository.findById(id), instanceId);
    }

    @RequestMapping(path="/find/{model}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Phone>> getByModel(@PathVariable String model) {
        return new ResData<Iterable<Phone>>(repository.findByModel(model), instanceId);
    }

    @RequestMapping(path="/new", method = RequestMethod.POST)
    public @ResponseBody ResData<String> addPhone(@RequestParam(value = "id", required = true) Integer id,
                                         @RequestParam(value = "model", required = true) String model,
                                         @RequestParam(value = "company", required = true) String company,
                                         @RequestParam(value = "purchaser", required = true) String purchaser,
                                         @RequestParam(value = "version", required = true) String version,
                                         @RequestParam(value = "price", required = true) Float price) throws ParseException {
        Phone instance = new Phone();
        Date purchaseDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        instance.setId(id);
        instance.setModel(model);
        instance.setCompany(company);
        instance.setPurchaser(purchaser);
        instance.setVersion(version);
        instance.setPrice(price);
        instance.setPurchaseDate(purchaseDate);
        repository.save(instance);
        return new ResData<String>("Added purchase", instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResData<String> deletePhone(@PathVariable int id) {
        repository.deleteById(id);
        return new ResData<String>("Deleted", instanceId);
    }


    @RequestMapping(path="/update", method = RequestMethod.POST)
    public @ResponseBody ResData<String> updatePhone(@RequestParam String model,
                                       @RequestParam String company,
                                       @RequestParam String version,
                                       @RequestParam Float price) throws ParseException {
        repository.customUpdate(model, company, version, price);
        return new ResData<String>("Updated", instanceId);
    }
}