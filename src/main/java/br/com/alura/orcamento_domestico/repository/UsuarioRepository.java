package br.com.alura.orcamento_domestico.repository;

import br.com.alura.orcamento_domestico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String username);
}
