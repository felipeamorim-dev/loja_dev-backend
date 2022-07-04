package app.loja_dev.controllers;

import app.loja_dev.dto.ProdutoDTO;
import app.loja_dev.entities.Produto;
import app.loja_dev.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

    private final ProdutoService produtoService;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody ProdutoDTO produtoDto){
        LOGGER.info("Adicionando produto {} a base de dados", produtoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoDto.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoService.save(modelMapper.map(produtoDto, Produto.class)));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAll(){
        LOGGER.info("Buscando todos os produtos do banco de dados");
        return ResponseEntity.ok().body(
                produtoService.findAll()
                        .stream()
                        .map(produto -> modelMapper.map(produto, ProdutoDTO.class))
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id){
        LOGGER.info("Buscando produto id: {} no banco de dados", id);
        if(ObjectUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Id passado com parâmetro é inválido");
        } else {
            return ResponseEntity.ok().body(modelMapper.map(produtoService.findByID(id), ProdutoDTO.class));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@Valid @RequestBody ProdutoDTO produtoDTO, @PathVariable Long id){
        LOGGER.info("Atualizando dados do produto {}", id);
        Produto p = produtoService.findByID(id);
        if(ObjectUtils.isEmpty(p)){
            throw new IllegalArgumentException("Id do produto é inválido");
        }
        modelMapper.map(produtoDTO, p);
        return ResponseEntity.ok().body(modelMapper.map(produtoService.save(p), ProdutoDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        LOGGER.info("Excluindo o produto id: {} do banco de dados", id);
        if(ObjectUtils.isEmpty(produtoService.findByID(id))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto encontrado para realizar atualização de dados");
        } else {
            produtoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
