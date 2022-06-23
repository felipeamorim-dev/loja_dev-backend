package app.loja_dev.services;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Item;

import java.util.List;
import java.util.Optional;


public interface ItemService {

    Item addItem(ItemDTO itemDTO) throws Exception;

    List<ItemDTO> convertList(List<Item> items) throws Exception;

    Item findById(Long id);
}
