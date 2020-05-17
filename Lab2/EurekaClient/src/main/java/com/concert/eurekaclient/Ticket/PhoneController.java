package com.concert.eurekaclient.Ticket;

import com.concert.eurekaclient.Signature;
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
public class TicketController {

    @Autowired
    private TicketRepository repository;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody
    Signature<Iterable<Ticket>> getAll() {
        return new Signature<Iterable<Ticket>>(repository.findAll(), instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Signature<Optional<Ticket>> getById(@PathVariable int id) {
        return new Signature<Optional<Ticket>>(repository.findById(id), instanceId);
    }

    @RequestMapping(path="/find/{model}", method = RequestMethod.GET)
    public @ResponseBody
    Signature<Iterable<Ticket>> getByModel(@PathVariable String model) {
        return new Signature<Iterable<Ticket>>(repository.findByModel(model), instanceId);
    }

    @RequestMapping(path="/new", method = RequestMethod.POST)
    public @ResponseBody Signature<String> addTicket(@RequestParam(value = "id", required = true) Integer id,
                                                  @RequestParam(value = "model", required = true) String model,
                                                  @RequestParam(value = "company", required = true) String company,
                                                  @RequestParam(value = "purchaser", required = true) String purchaser,
                                                  @RequestParam(value = "version", required = true) String version,
                                                  @RequestParam(value = "price", required = true) Float price) throws ParseException {
        Ticket instance = new Ticket();
        Date purchaseDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        instance.setId(id);
        instance.setModel(model);
        instance.setCompany(company);
        instance.setPurchaser(purchaser);
        instance.setVersion(version);
        instance.setPrice(price);
        instance.setPurchaseDate(purchaseDate);
        repository.save(instance);
        return new Signature<String>("Added purchase", instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Signature<String> deleteTicket(@PathVariable int id) {
        repository.deleteById(id);
        return new Signature<String>("Deleted", instanceId);
    }


    @RequestMapping(path="/update", method = RequestMethod.POST)
    public @ResponseBody Signature<String> updateTicket(@RequestParam String model,
                                                     @RequestParam String company,
                                                     @RequestParam String version,
                                                     @RequestParam Float price) throws ParseException {
        repository.customUpdate(model, company, version, price);
        return new Signature<String>("Updated", instanceId);
    }
}