package app.loja_dev.services;

import app.loja_dev.entities.Default;
import app.loja_dev.repositories.DefaultRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;

public abstract class DefaultServiceImpl<E extends Default, ID extends Serializable> implements DefaultService<E, ID> {

    protected DefaultRepository<E, ID> repository;

    public DefaultServiceImpl(DefaultRepository<E, ID> defaultRepository){
        this.repository = defaultRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public E findByID(ID id){
        return repository.findById(id).orElseThrow( () -> new EntityNotFoundException("Entidade não encontrado."));
    }

    @Override
    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public E update(E entity, ID id) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public boolean deleteById(ID id) {
        if (!ObjectUtils.isEmpty(findByID(id))){
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
