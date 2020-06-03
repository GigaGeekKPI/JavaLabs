package com.Jlabs.ApiGateway.Clothes;

import com.Jlabs.ApiGateway.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RequestMapping(path="/clothes")
@Controller
@CrossOrigin
public class ClothesController {

    @Autowired
    private ClothesRepository repository;

    @RequestMapping(path = "" , method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Clothes>> getAll() {
        return repository.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Optional<Clothes>> getById(@PathVariable int id) {
        return repository.getById(id);
    }

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> addClothes(@RequestParam(value = "naming", required = true) String naming,
                             @RequestParam(value = "brand", required = true) String brand,
                             @RequestParam(value = "country", required = true) String country,
                             @RequestParam(value = "price", required = true) Float price,
                             @RequestParam(value = "rate", required = true) Integer rate) {
        return repository.addClothes(naming, brand, country, price, rate);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResData<String> deleteClothes(@PathVariable Integer id) {
        return repository.deleteClothes(id);
    }


    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResData<String> updateClothes(@PathVariable int id,
                                  @RequestParam(value = "naming", required = true) String naming,
                                  @RequestParam(value = "brand", required = true) String brand,
                                  @RequestParam(value = "country", required = true) String country,
                                  @RequestParam(value = "price", required = true) Float price,
                                  @RequestParam(value = "rate", required = true) Integer rate) throws ParseException {
        return repository.updateClothes(id, naming, brand, country, price, rate);
    }
}