package com.concert.eurekaclient.Ticket;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.Modifying;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    @Query("SELECT u FROM Ticket u WHERE u.model = ?1")
    Iterable<Ticket> findByModel(String model);

    @Transactional
    @Modifying
    @Query("UPDATE Ticket u SET u.company = :company, u.version = :version, u.price = :price WHERE model = :model")
    Integer customUpdate(@Param("model") String model,
                         @Param("company") String company,
                         @Param("version") String version,
                         @Param("price") Float price);
}