package com.Jlabs.ApiGateway.Purchase;

import com.Jlabs.ApiGateway.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RequestMapping(path="/purchase")
@Controller
public class PurchaseController {

    @Autowired
    private PurchaseRepository repository;

    @RequestMapping(path = "" , method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Purchase>> getAll() {
        return repository.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Optional<Purchase>> getById(@PathVariable int id) {
        return repository.getById(id);
    }

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> addPurchase(@RequestParam(value = "customerId", required = true) Integer customerId,
                                @RequestParam(value = "total", required = true) Float total,
                                @RequestParam(value = "clothesId", required = true) Integer clothesId) {
        return repository.addPurchase(customerId, total, clothesId);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResData<String> deletePurchase(@PathVariable Integer id) {
        return repository.deletePurchase(id);
    }


    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResData<String> updatePurchase(@PathVariable int id,
                                   @RequestParam Integer customerId,
                                   @RequestParam Float total,
                                   @RequestParam Integer clothesId) throws ParseException {
        return repository.updatePurchase(id, customerId, total, clothesId);
    }
}