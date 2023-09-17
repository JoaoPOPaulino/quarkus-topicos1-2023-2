package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public List<Usuario> findByDescricao(String descricao) {
        return find("UPPER(descricao) LIKE UPPER(?1) ", "%" + descricao + "%").list();
    }

}
