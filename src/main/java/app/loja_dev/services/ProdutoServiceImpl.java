package app.loja_dev.services;

import app.loja_dev.entities.Produto;
import app.loja_dev.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Transactional
    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }
    
    @Transactional
    @Override
    public Produto findByID(Long id){
        return produtoRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Entidade não encontrado."));
    }

    @Transactional
    @Override
    public List<Produto> findAllById(List<Long> ids) {
        return produtoRepository.findAllById(ids);
    }
    
    @Transactional
    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }
    
    @Transactional
    @Override
    public Produto update(Produto produto, Long id) {
        return produtoRepository.save(produto);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (!ObjectUtils.isEmpty(findByID(id))){
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
