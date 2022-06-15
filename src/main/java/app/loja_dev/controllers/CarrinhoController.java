package app.loja_dev.controllers;

import app.loja_dev.dto.ItemDTO;
import app.loja_dev.entities.Carrinho;
import app.loja_dev.services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping(path = "/{usuarioId}")
    public ResponseEntity<Carrinho> create(@PathVariable Long usuarioId){
        try {
            return new ResponseEntity<Carrinho>(carrinhoService.create(usuarioId), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "createError");
        }
    }

    @PostMapping(path = "/{usuarioId}/item")
    public ResponseEntity<?> addItemCarrinho(@Valid @RequestBody ItemDTO itemDTO, @PathVariable Long usuarioId){
        try {
            return ResponseEntity.ok(carrinhoService.addItemCarrinho(itemDTO, usuarioId));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "erroAddItem");
        }
    }
}
