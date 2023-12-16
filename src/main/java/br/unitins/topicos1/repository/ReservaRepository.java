package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Reserva;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReservaRepository implements PanacheRepositoryBase<Reserva, Long> {
    public List<Reserva> findByUsuario(Long usuarioId) {
        return list("usuario.id", usuarioId);
    }

    public List<Reserva> findByUsuarioLogin(String login) {
        return list("from Reserva where usuario.login = ?1", login);
    }

}
