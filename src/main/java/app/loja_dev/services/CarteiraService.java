package app.loja_dev.services;

import app.loja_dev.entities.Carteira;
import app.loja_dev.entities.Default;

public interface CarteiraService extends DefaultService<Carteira, Long>{
    Double comprarMoeda(Double valor, Long usuarioId);
    Double saldo(Long usuarioId);
    Double descontaSaldo(Double valor, Long usuarioId);

}
