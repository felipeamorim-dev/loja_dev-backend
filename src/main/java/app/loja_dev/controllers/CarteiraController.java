package app.loja_dev.controllers;

import app.loja_dev.dto.CarteiraRequestDTO;
import app.loja_dev.services.CarteiraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;


@RestController
@RequestMapping(path = "/api/carteiras")
public class CarteiraController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarteiraController.class);

    @Autowired
    private CarteiraService carteiraService;

    @GetMapping(path = "/saldo/{usuarioId}")
    public ResponseEntity<?> getSaldo(@PathVariable Long usuarioId) {
        LOGGER.info("Buscando o saldo do usuário");
        if(ObjectUtils.isEmpty(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID do usuário inválido");
        }
        return ResponseEntity.ok(carteiraService.saldo(usuarioId));
    }

    @PostMapping(path = "/comprar")
    public ResponseEntity<?> compraMoeda(@Valid @RequestBody CarteiraRequestDTO carteiraRequestDTO){
        LOGGER.info("Realizando a compra de moedas");
        return ResponseEntity.ok(carteiraService.comprarMoeda(carteiraRequestDTO.getValor(), carteiraRequestDTO.getUsuarioId()));
    }

    @PostMapping(path = "/debitar")
    public ResponseEntity<?> debitarSaldo(@Valid @RequestBody CarteiraRequestDTO carteiraRequestDTO){
        LOGGER.info("Realizando transação de débito em conta");
        return ResponseEntity.ok(carteiraService.descontaSaldo(carteiraRequestDTO.getValor(), carteiraRequestDTO.getUsuarioId()));
    }

}
