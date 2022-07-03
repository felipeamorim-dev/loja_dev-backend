package app.loja_dev.services;

import app.loja_dev.dto.PedidoDTO;
import app.loja_dev.entities.Pedido;
import app.loja_dev.enums.StatusPedido;
import app.loja_dev.exceptions.ObjectNotFoundExceptions;
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
    private CarrinhoService carrinhoService;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Pedido findPedidoById(Long id) {
        return pedidoRepository.findPedidoById(id).orElseThrow(() -> new ObjectNotFoundExceptions("Pedido não encontrado"));
    }

    @Override
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

        pedido.setTotal(pedido.getItens().stream().map(item -> item.calcularCusto())
                .reduce(0.0, (elemento1, elemento2) -> elemento1 + elemento2));

        pedido.getItens().stream().forEach(item -> {
            carrinhoService.deleteItem(pedido.getUsuario().getId(), item.getId());
        });

        return pedido;
    }

    @Override
    public List<Pedido> findAllPedidoPerStatusAndUsuarioId(Integer status, Long usuarioId) {

        List<Pedido> pedidos = em.createQuery("SELECT DISTINCT p FROM Pedido p JOIN FETCH p.itens ip " +
                        "JOIN FETCH ip.produto " +
                        "JOIN FETCH p.usuario u " +
                        "JOIN FETCH u.carteira " +
                        "WHERE p.usuario.id = :usuario_id and p.statusPedido = :status", Pedido.class)
                .setParameter("usuario_id", usuarioId)
                .setParameter("status", StatusPedido.of(status))
                .getResultList();

        return pedidos;
    }

    @Override
    public List<Pedido> findAllPedidoPerUsuarioId(Long usuarioId) {
        List<Pedido> pedidos = em.createQuery("SELECT DISTINCT p FROM Pedido p JOIN FETCH p.itens ip " +
                        "JOIN FETCH ip.produto " +
                        "JOIN FETCH p.usuario u " +
                        "JOIN FETCH u.carteira " +
                        "WHERE p.usuario.id = :usuario_id", Pedido.class)
                .setParameter("usuario_id", usuarioId)
                .getResultList();

        return pedidos;
    }

    @Override
    public void updateStatusPedido(Integer status, Long pedidoId) {
        Pedido pedido = findPedidoById(pedidoId);
        pedido.setStatusPedido(StatusPedido.of(status));
        em.persist(pedido);
    }
}
