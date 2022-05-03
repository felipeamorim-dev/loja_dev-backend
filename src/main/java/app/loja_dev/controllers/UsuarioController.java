package app.loja_dev.controllers;

import app.loja_dev.dto.ProdutoDTO;
import app.loja_dev.dto.UsuarioDTO;
import app.loja_dev.entities.Produto;
import app.loja_dev.entities.Usuario;
import app.loja_dev.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Usuario usuario){
        try {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(modelMapper.map(usuarioService.save(usuario), UsuarioDTO.class));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar salvar o produto");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){

        try {
            return ResponseEntity.ok().body(
                    usuarioService.findAll()
                            .stream()
                            .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                            .collect(Collectors.toList()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar buscar usuarios.");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            if(ObjectUtils.isEmpty(id)) {
                return new ResponseEntity("Usuário não encontrado", HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.ok().body(usuarioService.findByID(id));
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum usuário foi encontrado.");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizar(@Valid @RequestBody Usuario usuario, @PathVariable Long id){
        try{
            Usuario u = usuarioService.findByID(id);
            if(ObjectUtils.isEmpty(u)){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado para realizar atualização de dados");
            }
            modelMapper.map(usuario, u);
            usuarioService.save(u);
            return ResponseEntity.ok().body(modelMapper.map(usuarioService.save(u), UsuarioDTO.class));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro na atualização dos dados do usuário.");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            if(ObjectUtils.isEmpty(usuarioService.findByID(id))){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado para realizar atualização de dados");
            } else {
                usuarioService.deleteById(id);
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível deletar o usuário.");
        }
    }
}
