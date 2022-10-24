package app.loja_dev.services;

import app.loja_dev.entities.Carteira;
import app.loja_dev.exceptions.InsufficientBalanceException;
import app.loja_dev.exceptions.ObjectNotFoundExceptions;
import app.loja_dev.repositories.CarteiraRepository;
import app.loja_dev.repositories.DefaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CarteiraServiceImpl implements CarteiraService{

    private static final double ZERO = 0.0;

    @Autowired
    private CarteiraRepository carteiraRepository;

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

    @Override
    @Transactional
    public List<Carteira> findAll() {
        return carteiraRepository.findAll();
    }

    @Override
    @Transactional
    public Carteira findByID(Long id){
        return carteiraRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Entidade não encontrado."));
    }

    @Override
    @Transactional
    public Carteira save(Carteira entity) {
        return carteiraRepository.save(entity);
    }

    @Override
    @Transactional
    public Carteira update(Carteira entity, Long id) {
        return carteiraRepository.save(entity);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if (!ObjectUtils.isEmpty(findByID(id))){
            carteiraRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
