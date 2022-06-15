package app.loja_dev.services;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Carrinho;
import app.loja_dev.entities.Item;
import app.loja_dev.entities.Usuario;
import app.loja_dev.exceptions.ObjectNotFoundExceptions;
import app.loja_dev.repositories.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityExistsException;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.*;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ItemService itemService;

    @Override
    public Carrinho findByUsuario(Long usuarioId) {
        return carrinhoRepository.findByUsuario(usuarioId)
                .orElseThrow(() -> new ObjectNotFoundExceptions("Carrinho do usuário não encontrado"));
    }

    @Override
    public Carrinho create(Long usuarioId) throws Exception {
        Carrinho car = new Carrinho();
        Usuario user = usuarioService.findByID(usuarioId);
        if (!isEmpty(user)) {
            car.setUsuario(user);
        }
        return carrinhoRepository.save(car);
    }

    @Override
    public Carrinho addItemCarrinho(ItemDTO itemDTO, Long usuarioId) throws Exception {
        Carrinho car = findByUsuario(usuarioId);
        Item item = itemService.addItem(itemDTO);
        int count = car.getItens()
                .stream()
                .filter(isItem -> isItem.getProduto().getId().equals(itemDTO.getProdutoId()))
                .collect(Collectors.toList()).size();

        if (count > 0) {
            throw new EntityExistsException("O item já foi adicionado a lista");
        }
        car.getItens().add(item);
        return carrinhoRepository.save(car);
    }


}
