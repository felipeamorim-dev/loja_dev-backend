package app.loja_dev.services;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Item;


public interface ItemService {

    Item addItem(ItemDTO itemDTO) throws Exception;
}
