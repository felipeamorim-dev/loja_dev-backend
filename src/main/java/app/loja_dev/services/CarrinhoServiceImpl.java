package app.loja_dev.services;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Carrinho;
import app.loja_dev.entities.Item;
import app.loja_dev.entities.Usuario;
import app.loja_dev.exceptions.ObjectNotFoundExceptions;
import app.loja_dev.repositories.CarrinhoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.*;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public Carrinho findByUsuario(Long usuarioId) {
        return carrinhoRepository.findByUsuario(usuarioId)
                .orElseThrow(() -> new ObjectNotFoundExceptions("Carrinho do usuário não encontrado"));
    }

    @Override
    @Transactional
    public Carrinho create(Usuario usuario) {
        Carrinho car = new Carrinho();
        car.setUsuario(usuario);
        return carrinhoRepository.save(car);
    }

    @Override
    @Transactional
    public Carrinho addItemCarrinho(ItemDTO itemDTO, Long usuarioId) {
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
        return carrinhoRepository.saveAndFlush(car);
    }

    @Override
    @Transactional
    public List<ItemDTO> findAllItens(Long usuarioId) {
        Carrinho car = findByUsuario(usuarioId);
        return itemService.convertList(car.getItens());
    }

    @Override
    @Transactional
    public void updateItem(Long usuarioId, ItemDTO itemDTO) {
        Carrinho car = findByUsuario(usuarioId);
        List<Item> itemUpdate = car.getItens().stream().filter(item -> item.getId() == itemDTO.getId()).collect(Collectors.toList());

        if(itemUpdate.isEmpty()) {
            throw new ObjectNotFoundExceptions("Não foi possível encontrar o item no carrinho de compras para realizar a atualização dos dados");
        }

        int index = car.getItens().indexOf(itemUpdate.get(0));
        mapper.map(itemService.addItem(itemDTO), itemUpdate.get(0));
        car.getItens().add(index, itemUpdate.get(0));
        carrinhoRepository.save(car);
    }

    @Override
    @Transactional
    public void deleteItem(Long usuarioId, Long itemId) {
        Carrinho car = findByUsuario(usuarioId);
        List<Item> itemRemove = car.getItens().stream().filter(item -> item.getId() == itemId).collect(Collectors.toList());

        if(itemRemove.isEmpty()) {
            throw new ObjectNotFoundExceptions("Não foi possível encontrar o item no carrinho de compras");
        }

        int index = car.getItens().indexOf(itemRemove.get(0));
        car.getItens().remove(index);
        carrinhoRepository.saveAndFlush(car);
    }

    @Override
    @Transactional
    public void deleteCarrinho(Long usuarioId) {
        Carrinho car = findByUsuario(usuarioId);
        carrinhoRepository.deleteById(car.getId());
    }


}
