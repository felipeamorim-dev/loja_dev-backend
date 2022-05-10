package app.loja_dev.controllers;

import app.loja_dev.dto.CarrinhoRequestDTO;
import app.loja_dev.dto.ProdutoDTO;
import app.loja_dev.dto.UsuarioDTO;
import app.loja_dev.entities.Carrinho;
import app.loja_dev.entities.Carteira;
import app.loja_dev.entities.Produto;
import app.loja_dev.entities.Usuario;
import app.loja_dev.services.CarrinhoService;
import app.loja_dev.services.ProdutoService;
import app.loja_dev.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/carrinhos")
public class CarrinhoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(path = "/add/item")
    public ResponseEntity<?> adicionar(@Valid @RequestBody CarrinhoRequestDTO carrinhoRequestDTO) throws Exception {
        Usuario u = usuarioService.findByID(carrinhoRequestDTO.getUsuarioId());
        Produto prod = produtoService.findByID(carrinhoRequestDTO.getProdutoId());

        if(ObjectUtils.isEmpty(u) || ObjectUtils.isEmpty(prod)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário ou produto não existente!");
        }

        Carrinho carrinho = carrinhoService.findByUsuario(u.getId()).get();
        carrinho.setProdutos(prod);
        carrinho = carrinhoService.save(carrinho);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carrinho.getId()).toUri();
        return ResponseEntity.created(uri).body(carrinho);

    }

    @GetMapping(path = "/{usuarioId}")
    public ResponseEntity<?> criarCarrinho(@PathVariable Long usuarioId) throws Exception {
        Usuario u = usuarioService.findByID(usuarioId);

        if(u.getCarrinho() == null){
            Carrinho carrinho = new Carrinho();
            carrinho.setUsuario(u);
//            u.setCarrinho(carrinho);
//            usuarioService.update(u, u.getId());
            return ResponseEntity.ok(carrinhoService.save(carrinho));
        }else {
            return ResponseEntity.ok(u.getCarrinho());
        }
    }
}
