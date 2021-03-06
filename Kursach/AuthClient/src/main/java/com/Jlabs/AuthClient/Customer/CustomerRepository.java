package com.Jlabs.AuthClient.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer getById(Integer id);
    Customer getByEmail(String email);
}