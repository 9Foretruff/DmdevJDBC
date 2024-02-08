package com.foretruff.jdbc.starter.dao;

import com.foretruff.jdbc.starter.entity.TicketEntity;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {
    boolean delete(K id);

    E save(E entity);

    void update(E entity);

    List<E> findAll();

    Optional<E> findById(K id);

}
