package app.loja_dev.controllers;

import app.loja_dev.dto.PedidoDTO;
import app.loja_dev.entities.Pedido;
import app.loja_dev.entities.Usuario;
import app.loja_dev.services.PedidoService;
import app.loja_dev.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PedidoDTO pedidoDTO){
        try {
            return new ResponseEntity<>(pedidoService.createPedido(pedidoDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao adicionar o pedido do usuário");
        }
    }
}
