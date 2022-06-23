package app.loja_dev.services;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Item addItem(@Valid ItemDTO itemDTO) throws Exception {
        Item newItem = new Item();
        newItem.setQuantidade(itemDTO.getQuantidade());
        newItem.setPreco(itemDTO.getPreco());
        newItem.setProduto(produtoService.findByID(itemDTO.getProdutoId()));
        return newItem;
    }

    @Override
    public List<ItemDTO> convertList(List<Item> items) throws Exception {
        return items.stream().map(item -> mapper.map(item, ItemDTO.class)).collect(Collectors.toList());
    }


}