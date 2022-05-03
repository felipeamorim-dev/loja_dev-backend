package app.loja_dev.services;

import app.loja_dev.entities.ItemPedido;
import app.loja_dev.repositories.DefaultRepository;
import app.loja_dev.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoServiceImpl extends DefaultServiceImpl<ItemPedido, Long> implements ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoServiceImpl(DefaultRepository<ItemPedido, Long> defaultRepository) {
        super(defaultRepository);
    }
}
