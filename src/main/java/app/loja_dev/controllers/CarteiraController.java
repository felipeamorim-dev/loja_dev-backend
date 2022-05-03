package app.loja_dev.controllers;

import app.loja_dev.dto.CarteiraRequestDTO;
import app.loja_dev.services.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/carteiras")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @GetMapping(path = "/saldo/{usuarioId}")
    public ResponseEntity<?> getSaldo(@PathVariable Long usuarioId) {
        try{
            if(ObjectUtils.isEmpty(usuarioId)) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID do usuário inválido");
            }
            return ResponseEntity.ok(carteiraService.saldo(usuarioId));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao consultar o saldo");
        }
    }

    @PostMapping(path = "/comprar")
    public ResponseEntity<?> compraMoeda(@Valid @RequestBody CarteiraRequestDTO carteiraRequestDTO){
        try{
            return ResponseEntity.ok(carteiraService.comprarMoeda(carteiraRequestDTO.getValor(), carteiraRequestDTO.getUsuarioId()));
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao comprar moedas na loja.");
        }
    }

    @PostMapping(path = "/debitar")
    public ResponseEntity<?> debitarSaldo(@Valid @RequestBody CarteiraRequestDTO carteiraRequestDTO){
        try{
            return ResponseEntity.ok(carteiraService.descontaSaldo(carteiraRequestDTO.getValor(), carteiraRequestDTO.getUsuarioId()));
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro na operaão na operação de debito.");
        }
    }

}
