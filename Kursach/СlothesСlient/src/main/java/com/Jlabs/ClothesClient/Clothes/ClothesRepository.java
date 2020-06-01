package com.Jlabs.ClothesClient.Clothes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothesRepository extends CrudRepository<Clothes, Integer> {
    Clothes getById(Integer id);
}