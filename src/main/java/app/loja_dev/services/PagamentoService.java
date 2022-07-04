package app.loja_dev.services;

import app.loja_dev.dto.PagamentoDTO;
import app.loja_dev.entities.Pagamento;

public interface PagamentoService {

    Pagamento realizaPagamento(PagamentoDTO pagamentoDTO);
}
