package app.loja_dev.services;

import app.loja_dev.entities.Produto;

import java.util.List;

public interface ProdutoService extends DefaultService<Produto, Long>{

    List<Produto> findAllById(List<Long> ids);
}
