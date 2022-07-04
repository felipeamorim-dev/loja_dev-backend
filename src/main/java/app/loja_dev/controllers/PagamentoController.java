package app.loja_dev.controllers;

import app.loja_dev.dto.PagamentoDTO;
import app.loja_dev.services.PagamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(path = "/api/pagamentos")
public class PagamentoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoController.class);

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<?> realizaPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO){
        try{
            LOGGER.info("Realizando paramento do pedido: {}", pagamentoDTO.getPedidoId());
            return new ResponseEntity(pagamentoService.realizaPagamento(pagamentoDTO), HttpStatus.CREATED);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
