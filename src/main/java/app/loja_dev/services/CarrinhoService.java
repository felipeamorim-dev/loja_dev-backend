package app.loja_dev.services;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Carrinho;
import java.util.List;


public interface CarrinhoService {

    Carrinho findByUsuario( Long usuarioId);

    Carrinho create(Long usuarioId) throws Exception;

    Carrinho addItemCarrinho(ItemDTO itemDTO, Long usuarioId) throws Exception;

    List<ItemDTO> findAllItens(Long usuarioId) throws Exception;

    void updateItem(Long usuarioId, ItemDTO itemDTO) throws Exception;

    void deleteItem(Long usuarioId, Long itemId) throws Exception;

    void deleteCarrinho(Long usuarioId) throws Exception;
}
