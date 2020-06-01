package com.Jlabs.ClothesClient.Clothes;

import com.Jlabs.ClothesClient.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@Controller
@RequestMapping(path="/clothes")
public class ClothesController {

    @Autowired
    private ClothesRepository repository;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Clothes>> getAll() {
        return new ResData<Iterable<Clothes>>(repository.findAll(), instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody ResData<Optional<Clothes>> getById(@PathVariable int id) {
        return new ResData<Optional<Clothes>>(repository.findById(id), instanceId);
    }


    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> addClothes(@RequestParam(value = "naming", required = true) String naming,
                               @RequestParam(value = "brand", required = true) String brand,
                               @RequestParam(value = "country", required = true) String country,
                               @RequestParam(value = "price", required = true) Float price,
                               @RequestParam(value = "rate", required = true) Integer rate) throws ParseException {
        Clothes instance = new Clothes();
        instance.setNaming(naming);
        instance.setBrand(brand);
        instance.setCountry(country);
        instance.setPrice(price);
        instance.setRate(rate);
        repository.save(instance);
        return new ResData<String>("Added clothes", instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResData<String> deleteClothes(@PathVariable int id) {
        repository.deleteById(id);
        return new ResData<String>("Deleted", instanceId);
    }


    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResData<String> updateClothes(@PathVariable int id,
                                                       @RequestParam(value = "naming", required = true) String naming,
                                                       @RequestParam(value = "brand", required = true) String brand,
                                                       @RequestParam(value = "country", required = true) String country,
                                                       @RequestParam(value = "price", required = true) Float price,
                                                       @RequestParam(value = "rate", required = true) Integer rate) throws ParseException {
        Clothes instance = repository.getById(id);
        instance.setNaming(naming);
        instance.setBrand(brand);
        instance.setCountry(country);
        instance.setPrice(price);
        instance.setRate(rate);
        repository.save(instance);
        return new ResData<String>("Updated", instanceId);
    }
}