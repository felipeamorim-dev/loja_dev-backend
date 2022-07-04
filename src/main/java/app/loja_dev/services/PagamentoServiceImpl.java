package app.loja_dev.services;

import app.loja_dev.dto.PagamentoDTO;
import app.loja_dev.entities.Pagamento;
import app.loja_dev.entities.Pedido;
import app.loja_dev.enums.StatusPedido;
import app.loja_dev.exceptions.AuthorizationPaymentException;
import app.loja_dev.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public Pagamento realizaPagamento(PagamentoDTO pagamentoDTO) {
        Pedido pedido = pedidoService.findPedidoById(pagamentoDTO.getPedidoId());
        if(estarAutorizado(pedido.getUsuario().getId(), pedido.getTotal())) {
            double novoSaldo = carteiraService.descontaSaldo(pedido.getTotal(), pedido.getUsuario().getId());
            pedidoService.updateStatusPedido(1 , pedido.getId());
            pedido.setStatusPedido(StatusPedido.PAGO);
            return pagamentoRepository.saveAndFlush(new Pagamento(true, "Pagamento realizado com sucesso", pedido));
        } else {
            throw new AuthorizationPaymentException("Pagamento não autorizado.");
        }
    }

    private boolean estarAutorizado(Long usuarioId, Double total) {
        return carteiraService.saldo(usuarioId) >= total ? true : false;
    }
}
