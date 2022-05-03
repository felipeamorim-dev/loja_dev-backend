package app.loja_dev.services;

import app.loja_dev.entities.Pedido;
import app.loja_dev.repositories.DefaultRepository;
import app.loja_dev.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl extends DefaultServiceImpl<Pedido, Long> implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(DefaultRepository<Pedido, Long> defaultRepository) {
        super(defaultRepository);
    }
}
