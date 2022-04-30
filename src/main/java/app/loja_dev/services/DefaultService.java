package app.loja_dev.services;

import app.loja_dev.entities.Default;

import java.io.Serializable;
import java.util.List;

public interface DefaultService<E extends Default, ID extends Serializable> {

    public List<E> findAll() throws Exception;
    public E findByID(ID id) throws Exception;
    public E save(E entity) throws Exception;
    public E update(E entity, ID id) throws Exception;
    public boolean deleteById(ID id) throws Exception;

}
