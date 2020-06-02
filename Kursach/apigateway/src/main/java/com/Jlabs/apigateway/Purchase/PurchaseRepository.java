package com.Jlabs.ApiGateway.Purchase;

import com.Jlabs.ApiGateway.ResData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(value = "purchase")
public interface PurchaseRepository {

    @RequestMapping(path = "/purchase", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Purchase>> getAll();

    @RequestMapping(path = "/purchase/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Optional<Purchase>> getById(@PathVariable int id);

    @RequestMapping(path="/purchase", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> addPurchase(@RequestParam(value = "customerId", required = true) Integer customerId,
                                @RequestParam(value = "total", required = true) Float total,
                                @RequestParam(value = "clothesId", required = true) Integer clothesId);

    @RequestMapping(path = "/purchase/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResData<String> deletePurchase(@PathVariable Integer id);

    @RequestMapping(path="/purchase/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResData<String> updatePurchase(@PathVariable int id,
                                   @RequestParam Integer customerId,
                                   @RequestParam Float total,
                                   @RequestParam Integer clothesId);
}