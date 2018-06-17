package com.reaktorlabs.model.CRUDService;

import java.util.List;

public interface CRUDService<E,T> {
    
    E add(E element);

    E read(T id);

    E update(E element);

    void remove(T id);

    List<E> getAll();
    
}
