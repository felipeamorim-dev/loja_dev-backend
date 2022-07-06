package app.loja_dev.services;

import app.loja_dev.entities.Carteira;
import app.loja_dev.exceptions.InsufficientBalanceException;
import app.loja_dev.exceptions.ObjectNotFoundExceptions;
import app.loja_dev.repositories.CarteiraRepository;
import app.loja_dev.repositories.DefaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteiraServiceImpl extends DefaultServiceImpl<Carteira, Long> implements CarteiraService{

    private static final double ZERO = 0.0;

    @Autowired
    private CarteiraRepository carteiraRepository;

    public CarteiraServiceImpl(DefaultRepository<Carteira, Long> defaultRepository) {
        super(defaultRepository);
    }

    @Override
    public Double comprarMoeda(Double valor, Long usuarioId) {
        Carteira carteira = carteiraRepository.getByUsuario(usuarioId)
                .orElseThrow(() -> new ObjectNotFoundExceptions("Usuário não encontrado"));

        if (carteira.getSaldo() >= 0) {
            carteira.setSaldo(carteira.getSaldo() + valor);
        } else {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }
         return carteiraRepository.save(carteira).getSaldo();
    }

    @Override
    public Double saldo(Long usuarioId) {
        Carteira carteira = carteiraRepository.getByUsuario(usuarioId)
                .orElseThrow(() -> new ObjectNotFoundExceptions("Usuário não encontrado"));

        return carteira.getSaldo() > 0 ? carteira.getSaldo() : ZERO;
    }

    @Override
    public Double descontaSaldo(Double valor, Long usuarioId) {
        Carteira carteira = carteiraRepository.getByUsuario(usuarioId)
                .orElseThrow(() -> new ObjectNotFoundExceptions("Usuário não encontrado"));

        if (carteira.getSaldo() >= 0) {
            carteira.setSaldo(carteira.getSaldo() - valor);
        } else {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }

        return carteiraRepository.save(carteira).getSaldo();
    }
}
