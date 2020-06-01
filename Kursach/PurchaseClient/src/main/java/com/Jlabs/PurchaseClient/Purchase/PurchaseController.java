package com.Jlabs.PurchaseClient.Purchase;

import com.Jlabs.PurchaseClient.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping(path="/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseRepository repository;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Purchase>> getAll() {
        return new ResData<Iterable<Purchase>>(repository.findAll(), instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody ResData<Optional<Purchase>> getById(@PathVariable int id) {
        return new ResData<Optional<Purchase>>(repository.findById(id), instanceId);
    }

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody ResData<String> addPurchase(@RequestParam(value = "customerId", required = true) Integer customerId,
                                                  @RequestParam(value = "total", required = true) Float total,
                                                  @RequestParam(value = "clothesId", required = true) Integer clothesId) throws ParseException {
        Purchase instance = new Purchase();
        instance.setCustomerId(customerId);
        instance.setTotal(total);
        instance.setClothesId(clothesId);
        repository.save(instance);
        return new ResData<String>("Added purchase", instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResData<String> deletePurchase(@PathVariable int id) {
        repository.deleteById(id);
        return new ResData<String>("Deleted", instanceId);
    }


    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResData<String> updatePurchase(@PathVariable int id,
                                                     @RequestParam Integer customerId,
                                                     @RequestParam Float total,
                                                     @RequestParam Integer clothesId) throws ParseException {
        Purchase instance = repository.getById(id);
        instance.setCustomerId(customerId);
        instance.setTotal(total);
        instance.setClothesId(clothesId);
        repository.save(instance);
        return new ResData<String>("Updated", instanceId);
    }
}