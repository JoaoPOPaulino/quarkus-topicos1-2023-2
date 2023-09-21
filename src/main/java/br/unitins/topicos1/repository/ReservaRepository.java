package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReservaRepository implements PanacheRepositoryBase<Reserva, Long> {
    public List<Reserva> findByUsuario(Usuario usuario) {
        return find("UPPER(usuario) LIKE UPPER(?1) ", "%" + usuario + "%").list();
    }
}
