package com.reaktorlabs.logic.repository.CRUD;

import com.reaktorlabs.logic.repository.ejbException.EJBException;
import java.util.List;

public interface GenericCRUDRepo<E, T> {

    E create(E element) throws EJBException;

    E getById(T id)throws EJBException;

    E update(E element);

    void remove(T id);

    List<E> getAll();
}
