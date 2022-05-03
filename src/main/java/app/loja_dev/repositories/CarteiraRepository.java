package app.loja_dev.repositories;

import app.loja_dev.entities.Carteira;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends DefaultRepository<Carteira, Long>{

    Carteira getByUsuario(Long usuarioId);
}
