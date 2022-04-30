package app.loja_dev.repositories;

import app.loja_dev.entities.Produto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends DefaultRepository<Produto, Long> {
}
