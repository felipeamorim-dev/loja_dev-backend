package app.loja_dev.controllers;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Carrinho;
import app.loja_dev.services.CarrinhoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/carrinhos")
public class CarrinhoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarrinhoController.class);

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping(path = "/{usuarioId}")
    public ResponseEntity<List<ItemDTO>> findAllItensCarrinho(@PathVariable Long usuarioId){
        LOGGER.info("Busca itens adicionados ao carrinho pelo id {} do usuario", usuarioId);
        return ResponseEntity.ok(carrinhoService.findAllItens(usuarioId));
    }

    @PostMapping(path = "/{usuarioId}")
    public ResponseEntity<Carrinho> create(@PathVariable Long usuarioId){
        LOGGER.info("Vincula um carrinho de compras para o usuario pelo id {}", usuarioId);
        return new ResponseEntity<Carrinho>(carrinhoService.create(usuarioId), HttpStatus.CREATED);
    }

    @PostMapping(path = "/{usuarioId}/item")
    public ResponseEntity<?> addItemCarrinho(@Valid @RequestBody ItemDTO itemDTO, @PathVariable Long usuarioId){
        LOGGER.info("Adiciona o item {} ao carrinho de compras do usuario {}", itemDTO, usuarioId);
        return ResponseEntity.ok(carrinhoService.addItemCarrinho(itemDTO, usuarioId));

    }

    @PutMapping(path = "/{usuarioId}/item")
    public ResponseEntity<Void> updateItem(@Valid @RequestBody ItemDTO itemDTO, @PathVariable Long usuarioId) {
        LOGGER.info("Atualizando o item {} no arrinho de compras do usuario {}", itemDTO, usuarioId);
        carrinhoService.updateItem(usuarioId, itemDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{usuarioId}/item/{itemId}")
    public ResponseEntity<Void> deleteItemCarrinho(@PathVariable Long usuarioId, @PathVariable Long itemId) {
        LOGGER.info("Deletando o item {}, do carrinho do usuario {}", itemId, usuarioId);
        carrinhoService.deleteItem(usuarioId, itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{usuarioId}")
    public ResponseEntity<Void> deleteCarrinho(@PathVariable Long usuarioId) {
        LOGGER.info("Deletando o carrinho do usuario {}", usuarioId);
        carrinhoService.deleteCarrinho(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
