package com.Jlabs.eurekaclient.Phone;

import com.Jlabs.eurekaclient.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import java.util.HashMap;

@Controller
@RefreshScope
public class PhoneController {
    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Value("${test.prop5:}")
    private String prop5;

    @Value("${test.prop6:}")
    private String prop6;

    @Autowired
    private PhoneRepository repository;

    @RequestMapping(path="/config", method = RequestMethod.GET)
    public @ResponseBody HashMap<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("phone app prop5", prop5);
        configmap.put("phone app prop6", prop6);
        return configmap;
    }

    @RequestMapping(path="/phone", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Phone>> getAll() {
        return new ResData<Iterable<Phone>>(repository.findAll(), instanceId);
    }

    @RequestMapping(path="/phone/{id}", method = RequestMethod.GET)
    public @ResponseBody ResData<Optional<Phone>> getById(@PathVariable int id) {
        return new ResData<Optional<Phone>>(repository.findById(id), instanceId);
    }

    @RequestMapping(path="/phone/find/{model}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Phone>> getByModel(@PathVariable String model) {
        return new ResData<Iterable<Phone>>(repository.findByModel(model), instanceId);
    }

    @RequestMapping(path="/phone/new", method = RequestMethod.POST)
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

    @RequestMapping(path="/phone/{id}", method = RequestMethod.DELETE)
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