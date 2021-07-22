package com.caniksea.poll.rankinteractive.autogame.service;

import java.util.List;
import java.util.Optional;

public interface IService<T, ID> {
    T create(T t);
    Optional<T> readById(ID id);
    List<T> findAll();
}
