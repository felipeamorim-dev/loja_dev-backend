package app.loja_dev.services;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Carrinho;
import app.loja_dev.entities.Usuario;

import java.util.List;


public interface CarrinhoService {

    Carrinho findByUsuario( Long usuarioId);

    Carrinho create(Usuario usuario);

    Carrinho addItemCarrinho(ItemDTO itemDTO, Long usuarioId);

    List<ItemDTO> findAllItens(Long usuarioId);

    void updateItem(Long usuarioId, ItemDTO itemDTO);

    void deleteItem(Long usuarioId, Long itemId);

    void deleteCarrinho(Long usuarioId);
}
