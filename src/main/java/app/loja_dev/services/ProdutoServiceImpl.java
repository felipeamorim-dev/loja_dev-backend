package app.loja_dev.services;

import app.loja_dev.entities.Produto;
import app.loja_dev.repositories.DefaultRepository;
import app.loja_dev.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl extends DefaultServiceImpl<Produto, Long> implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(DefaultRepository<Produto, Long> defaultRepository){
        super(defaultRepository);
    }
}
