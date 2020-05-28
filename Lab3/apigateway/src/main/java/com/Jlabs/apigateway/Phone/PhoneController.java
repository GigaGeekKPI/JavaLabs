package com.Jlabs.apigateway.Phone;

import com.Jlabs.apigateway.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

import java.util.HashMap;
import com.Jlabs.apigateway.ConfigClientAppConfiguration;
import java.util.Map;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@Controller
public class PhoneController {

    @Autowired
    private PhoneRepository repository;

    @Autowired
    ConfigClientAppConfiguration configClientAppConfiguration;

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("Jlabs prop1", configClientAppConfiguration.getProp1());
        configmap.put("Jlabs prop2", configClientAppConfiguration.getProp2());
        configmap.put("Jlabs prop3", configClientAppConfiguration.getProp3());
        configmap.put("Jlabs prop4", configClientAppConfiguration.getProp4());
        configmap.putAll(repository.getConfig());
        return configmap;
    }

    @RequestMapping(path = "/phone" , method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Phone>> getAll() {
        return repository.getAll();
    }

    @RequestMapping(path = "/phone/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Optional<Phone>> getById(@PathVariable int id) {
        return repository.getById(id);
    }

    @RequestMapping(path="/phone/find/{model}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Phone>> getByModel(@PathVariable String model) {
        return repository.getByModel(model);
    }

    @RequestMapping(path="/phone/new", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> addPhone(@RequestParam(value = "id", required = true) Integer id,
                             @RequestParam(value = "model", required = true) String model,
                             @RequestParam(value = "company", required = true) String company,
                             @RequestParam(value = "purchaser", required = true) String purchaser,
                             @RequestParam(value = "version", required = true) String version,
                             @RequestParam(value = "price", required = true) Float price) {
        return repository.addPhone(id, model, company, purchaser, version, price);
    }

    @RequestMapping(path = "/phone/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResData<String> deleteCustomer(@PathVariable Integer id) {
        return repository.deletePhone(id);
    }


    @RequestMapping(path="/phone/update", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> update(@RequestParam(value = "model", required = true) String model,
                           @RequestParam(value = "company", required = true) String company,
                           @RequestParam(value = "version", required = true) String version,
                           @RequestParam(value = "price", required = true) Float price) throws ParseException {
        return repository.updatePhone(model, company, version, price);
    }
}