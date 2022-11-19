package app.loja_dev.services;

import app.loja_dev.entities.Carrinho;
import app.loja_dev.entities.Carteira;
import app.loja_dev.entities.Usuario;
import app.loja_dev.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private CarteiraService carteiraService;
    @Autowired
    private CarrinhoService carrinhoService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional
    @Override
    public Usuario findByID(Long id){
        return usuarioRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Usuário não encontrado."));
    }

    @Override
    @Transactional
    public Usuario findByNomeUsuario(String nomeUsuario) {
        return usuarioRepository.findByNomeUsuario(nomeUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    @Transactional
    @Override
    public Usuario save(Usuario usuario) {

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario usuarioResult = usuarioRepository.save(usuario);
        Carteira carteira = new Carteira(usuarioResult, 0.0);
        carteiraService.save(carteira);
        Carrinho car = carrinhoService.create(usuarioResult);
        usuarioResult.setCarrinho(car);
        usuarioResult = usuarioRepository.save(usuarioResult);
        return usuarioResult;
    }

    @Transactional
    @Override
    public Usuario update(Usuario entity, Long id) {
        return usuarioRepository.save(entity);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (!ObjectUtils.isEmpty(findByID(id))){
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
