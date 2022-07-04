package app.loja_dev.services;

import app.loja_dev.entities.Default;

import java.io.Serializable;
import java.util.List;

public interface DefaultService<E extends Default, ID extends Serializable> {

    public List<E> findAll();
    public E findByID(ID id);
    public E save(E entity);
    public E update(E entity, ID id);
    public boolean deleteById(ID id);

}
