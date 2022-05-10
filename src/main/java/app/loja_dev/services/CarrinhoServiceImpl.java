package app.loja_dev.services;

import app.loja_dev.entities.Carrinho;
import app.loja_dev.repositories.CarrinhoRepository;
import app.loja_dev.repositories.DefaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoServiceImpl extends DefaultServiceImpl<Carrinho, Long> implements CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public CarrinhoServiceImpl(DefaultRepository<Carrinho, Long> defaultRepository) {
        super(defaultRepository);
    }

    @Override
    public Optional<Carrinho> findByUsuario(Long usuarioId) {
        return carrinhoRepository.findByUsuario(usuarioId);
    }
}
