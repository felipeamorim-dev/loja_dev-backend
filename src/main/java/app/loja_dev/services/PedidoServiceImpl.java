package app.loja_dev.services;

import app.loja_dev.dto.PedidoDTO;
import app.loja_dev.entities.ItemPedido;
import app.loja_dev.entities.Pedido;
import app.loja_dev.exceptions.ObjectNotFoundExceptions;
import app.loja_dev.repositories.ItemPedidoRepository;
import app.loja_dev.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CarrinhoService carrinhoService;

    @PersistenceContext
    private EntityManager em;

    public Pedido createPedido(PedidoDTO pedidoDTO) {

        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();

        sb.append("INSERT INTO pedido(status, data_compra, usuario_id) VALUES(?, ?, ?)");
        sb1.append("INSERT INTO item_pedido(item_id, pedido_id) VALUES(?, ?)");

        em.createNativeQuery(sb.toString(), Pedido.class)
                .setParameter(1, 0)
                .setParameter(2, pedidoDTO.getMomento())
                .setParameter(3, pedidoDTO.getUsuarioId())
                .executeUpdate();

        Pedido pedido = pedidoRepository.findByPedidoPerMomentAndUsuarioId(pedidoDTO.getMomento(), pedidoDTO.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        pedidoDTO.getItens().stream().forEach(item -> {
            em.createNativeQuery(sb1.toString())
                    .setParameter(1, item.getId())
                    .setParameter(2, pedido.getId())
                    .executeUpdate();
        });

        List<ItemPedido> itemPedido = itemPedidoRepository.findAllPerPedidoId(pedido.getId());
        itemPedido.stream().forEach(obj -> {
            pedido.getItens().add(itemService.findById(obj.getItemId()));
        });

        pedido.getItens().stream().forEach(item -> {
            carrinhoService.deleteItem(pedido.getUsuario().getId(), item.getId());
        });

        return pedido;
    }
}
