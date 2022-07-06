package app.loja_dev.repositories;

import app.loja_dev.entities.Carteira;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends DefaultRepository<Carteira, Long>{

    @Query(nativeQuery = true, value = "select c.* from carteira c where c.id_usuario = :usuarioId")
    Optional<Carteira> getByUsuario(@Param("usuarioId") Long usuarioId);
}
