package app.loja_dev.controllers;

import app.loja_dev.dto.UsuarioDTO;
import app.loja_dev.entities.Carteira;
import app.loja_dev.entities.Usuario;
import app.loja_dev.services.CarteiraService;
import app.loja_dev.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody Usuario usuario){
        try {
            LOGGER.info("Validando dados do usuario: {}", usuario.getNome());
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
            Usuario u = usuarioService.save(usuario);
            LOGGER.info("Salvando dados do usuario no banco de dados");

            return ResponseEntity.created(uri).body(modelMapper.map(u, UsuarioDTO.class));
        } catch (Exception e) {
            LOGGER.error("Erro ao tentar realizar o salvamento do usuario no banco de dados");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar salvar o usuário");
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll(){

        LOGGER.info("Buscando todos os usuarios no banco de dados");
        return ResponseEntity.ok().body(
                usuarioService.findAll()
                        .stream()
                        .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){

        LOGGER.info("Validando o id para recuperar os dados do usuari no banco de dados");
        if(ObjectUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Id do usuario não encontrado");
        } else {
            LOGGER.info("Buscando dados do usuario pelo id: {}", id);
            return ResponseEntity.ok().body(modelMapper.map(usuarioService.findByID(id), UsuarioDTO.class));
        }
    }

    @GetMapping(value = "/nome/{nomeUsuario}")
    public ResponseEntity<?> getPerUsername(@PathVariable String nomeUsuario){

        LOGGER.info("Validando o nome do usuário para recuperar os seus dados");
        if(ObjectUtils.isEmpty(nomeUsuario)) {
            throw new IllegalArgumentException("usuario não encontrado");
        } else {
            LOGGER.info("Buscando dados do usuario pelo username: {}", nomeUsuario);
            return ResponseEntity.ok().body(modelMapper
                    .map(usuarioService.findByNomeUsuario(nomeUsuario), UsuarioDTO.class));
        }
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizar(@Valid @RequestBody Usuario usuario, @PathVariable Long id){

        LOGGER.info("Validando o usuario para realizar atualização de seus dados");
        Usuario u = usuarioService.findByID(id);
        modelMapper.map(usuario, u);
        usuarioService.save(u);
        LOGGER.info("Dados do usuario {} atualizados", id);
        return ResponseEntity.ok().body(modelMapper.map(usuarioService.save(u), UsuarioDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        LOGGER.info("Validação do usuário pelo id {} para exclusão de registro", id);
        if(ObjectUtils.isEmpty(usuarioService.findByID(id))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado para realizar atualização de dados");
        } else {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
