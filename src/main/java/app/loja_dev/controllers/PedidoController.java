package app.loja_dev.controllers;

import app.loja_dev.dto.PedidoDTO;
import app.loja_dev.dto.response.PedidoResponse;
import app.loja_dev.entities.Pedido;
import app.loja_dev.services.PedidoService;
import app.loja_dev.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/pedidos")
public class PedidoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PedidoDTO pedidoDTO){
        LOGGER.info("Criando pedido: {}", pedidoDTO);
        Pedido pedido = pedidoService.createPedido(pedidoDTO);
        return new ResponseEntity<>(mapper.map(pedido, PedidoResponse.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAllPedidoPerStatusAndUsuario(@RequestParam("usuarioId") Long usuarioId, @RequestParam(name = "status") Integer status) {
        LOGGER.info("Buscando pedido do usuario: {} com status: {}", usuarioId, status);
        return ResponseEntity.ok(pedidoService.findAllPedidoPerStatusAndUsuarioId(status, usuarioId).stream()
                .map(pedido -> mapper.map(pedido,  PedidoResponse.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "/usuario")
    public ResponseEntity<?> findAllPedidoPerUsuario(@RequestParam("usuarioId") Long usuarioId) {
        LOGGER.info("Buscando pedido do usuario: {}", usuarioId);
        return ResponseEntity.ok(pedidoService.findAllPedidoPerUsuarioId(usuarioId).stream()
                .map(pedido -> mapper.map(pedido,  PedidoResponse.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "/encontrar")
    public ResponseEntity<?> findByPedidoId(@RequestParam("pedidoId") Long pedidoId){
        LOGGER.info("Buscando pedido pelo id: {}", pedidoId);
        return ResponseEntity.ok(mapper.map(pedidoService.findPedidoById(pedidoId), PedidoResponse.class));
    }

    @PutMapping(path = "/atualizar")
    public ResponseEntity<?> updateStatusPedido(@RequestParam Map<String, String> allParams) {
        if (allParams.containsKey("status") && allParams.containsKey("pedidoId")) {
            LOGGER.info("Atualizando o pedido: {}, para o status: {}", allParams.get("pedidoId"), allParams.get("status"));
            pedidoService.updateStatusPedido(Integer.parseInt(allParams.get("status")), Long.parseLong(allParams.get("pedidoId")));
            return ResponseEntity.noContent().build();
        } else {
            throw new IllegalArgumentException("Status ou id do projeto inválido");
        }
    }
}
