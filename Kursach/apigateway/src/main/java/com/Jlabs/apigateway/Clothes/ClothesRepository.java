package com.Jlabs.ApiGateway.Clothes;

import com.Jlabs.ApiGateway.ResData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(value = "clothes")
public interface ClothesRepository {

    @RequestMapping(path = "/clothes", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Clothes>> getAll();

    @RequestMapping(path = "/clothes/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Optional<Clothes>> getById(@PathVariable int id);

    @RequestMapping(path="/clothes", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> addClothes(@RequestParam(value = "naming", required = true) String naming,
                               @RequestParam(value = "brand", required = true) String brand,
                               @RequestParam(value = "country", required = true) String country,
                               @RequestParam(value = "price", required = true) Float price,
                               @RequestParam(value = "rate", required = true) Integer rate);

    @RequestMapping(path = "/clothes/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResData<String> deleteClothes(@PathVariable Integer id);

    @RequestMapping(path="/clothes/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResData<String> updateClothes(@PathVariable int id,
                                  @RequestParam(value = "naming", required = true) String naming,
                                  @RequestParam(value = "brand", required = true) String brand,
                                  @RequestParam(value = "country", required = true) String country,
                                  @RequestParam(value = "price", required = true) Float price,
                                  @RequestParam(value = "rate", required = true) Integer rate);
}