package app.loja_dev.controllers;

import app.loja_dev.dto.ProdutoDTO;
import app.loja_dev.entities.Produto;
import app.loja_dev.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody ProdutoDTO produtoDto){
        try {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoDto.getId()).toUri();
            return ResponseEntity.created(uri).body(produtoService.save(modelMapper.map(produtoDto, Produto.class)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar salvar o produto");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){

        try {
            return ResponseEntity.ok().body(
                    produtoService.findAll()
                            .stream()
                            .map(produto -> modelMapper.map(produto, ProdutoDTO.class))
                            .collect(Collectors.toList()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum produto encontrado.");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            if(ObjectUtils.isEmpty(id)) {
                return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.ok().body(produtoService.findByID(id));
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum produto foi encontrado.");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizar(@Valid @RequestBody ProdutoDTO produtoDTO, @PathVariable Long id){
        try{
            Produto p = produtoService.findByID(id);
            if(ObjectUtils.isEmpty(p)){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto encontrado para realizar atualização de dados");
            }
            modelMapper.map(produtoDTO, p);
            produtoService.save(p);
            return ResponseEntity.ok().body(p);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro na atualização do produto.");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            if(ObjectUtils.isEmpty(produtoService.findByID(id))){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto encontrado para realizar atualização de dados");
            } else {
                produtoService.deleteById(id);
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível deletar o produto.");
        }
    }
}
