package com.gateway.apigateway.Phone;

import com.gateway.apigateway.ResData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(value = "phone-app")
@RequestMapping(path="/phone")
public interface PhoneRepository {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Phone>> getAll();

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Optional<Phone>> getById(@PathVariable int id);

    @RequestMapping(path="/find/{model}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Phone>> getByModel(@PathVariable String model);

    @RequestMapping(path="/new", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> addPhone(@RequestParam(value = "id", required = true) Integer id,
                             @RequestParam(value = "model", required = true) String model,
                             @RequestParam(value = "company", required = true) String company,
                             @RequestParam(value = "purchaser", required = true) String purchaser,
                             @RequestParam(value = "version", required = true) String version,
                             @RequestParam(value = "price", required = true) Float price);

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResData<String> deletePhone(@PathVariable Integer id);

    @RequestMapping(path="/update", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> updatePhone(@RequestParam(value = "model", required = true) String model,
                                @RequestParam(value = "company", required = true) String company,
                                @RequestParam(value = "version", required = true) String version,
                                @RequestParam(value = "price", required = true) Float price);
}