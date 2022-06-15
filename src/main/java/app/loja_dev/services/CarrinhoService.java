package app.loja_dev.services;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Carrinho;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarrinhoService {

    Carrinho findByUsuario(@Param("usuarioId") Long usuarioId);

    Carrinho create(@Param("usuarioId") Long usuarioId) throws Exception;

    Carrinho addItemCarrinho(ItemDTO itemDTO, Long usuarioId) throws Exception;
}
