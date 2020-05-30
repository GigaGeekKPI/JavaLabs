package com.Jlabs.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository repository;

    public Phone Add(Phone phone) throws CustomException {
        SanityChecks(phone);
        return repository.save(phone);
    }

    public void DeleteById(Integer id) throws CustomException {
        Phone existing = repository.findById(id).orElseThrow(() -> new CustomException("Phone wasn't found"));
        if (existing.isDeleted()) throw new CustomException("Phone was deleted");
        existing.setDeleted(true);
        repository.save(existing);
    }

    public Phone Update(Integer id, Phone item) throws CustomException, ParseException {
        Phone phone = repository.findById(item.getId()).orElseThrow(() -> new CustomException("Phone wasn't found"));
        if (phone.isDeleted()) throw new CustomException("Phone wasn't found");
        SanityChecks(item);

        repository.customUpdate(phone.getModel(),
                phone.getCompany(),
                phone.getVersion(),
                phone.getPrice());
        return phone;
    }

    private void SanityChecks(Phone item) throws CustomException {
        if (item.getModel() == null || item.getCompany() == null || item.getVersion() == null ||
                item.getPurchaser() == null || item.getPrice() == 0.0)
            throw new CustomException("Incorrect data");
    }

    public ResData<List<Phone>> GetAll(String id) {
        List<Phone> list = new ArrayList<>();
        repository.findAll().forEach(list::add);

        List<Phone> filtered = new ArrayList<>();
        for (Phone item : list) {
            if (!item.isDeleted()) {
                filtered.add(item);
            }
        }
        return new ResData<>(filtered, id);
    }

    public ResData<Phone> GetById(Integer id, String str) throws CustomException {
        Phone res = repository.findById(id).orElseThrow(() -> new CustomException("Phone wasn't found"));
        if (res == null | res.isDeleted()) throw new CustomException("Phone was deleted");
        return new ResData<>(res, str);
    }
}