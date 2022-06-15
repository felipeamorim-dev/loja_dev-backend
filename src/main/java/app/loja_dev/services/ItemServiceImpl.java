package app.loja_dev.services;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ProdutoService produtoService;

    @Override
    public Item addItem(@Valid ItemDTO itemDTO) throws Exception {
        Item newItem = new Item();
        newItem.setQuantidade(itemDTO.getQuantidade());
        newItem.setPreco(itemDTO.getPreco());
        newItem.setProduto(produtoService.findByID(itemDTO.getProdutoId()));
        return newItem;
    }
}
