package app.loja_dev.services;

import app.loja_dev.entities.Carrinho;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarrinhoService extends DefaultService<Carrinho, Long> {

    Optional<Carrinho> findByUsuario(@Param("usuarioId") Long usuarioId);
}
