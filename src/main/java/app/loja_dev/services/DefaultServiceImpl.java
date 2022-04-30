package app.loja_dev.services;

import app.loja_dev.entities.Default;
import app.loja_dev.entities.Produto;
import app.loja_dev.repositories.DefaultRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.List;

public abstract class DefaultServiceImpl<E extends Default, ID extends Serializable> implements DefaultService<E, ID> {

    protected DefaultRepository<E, ID> repository;

    public DefaultServiceImpl(DefaultRepository<E, ID> defaultRepository){
        this.repository = defaultRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    @Transactional
    public E findByID(ID id) throws Exception {
        return repository.findById(id).orElseThrow( () -> new RuntimeException("Entidade não encontrado."));
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public E update(E entity, ID id) throws Exception {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public boolean deleteById(ID id) throws Exception {
        if (!ObjectUtils.isEmpty(findByID(id))){
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
