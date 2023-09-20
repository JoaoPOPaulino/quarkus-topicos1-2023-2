package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Reserva;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ReservaRepository implements PanacheRepositoryBase<Reserva, Long> {
    public List<QuartoHotel> findByUsuario(Usuario usuario) {
        return find("UPPER(usuario) LIKE UPPER(?1) ", "%" + usuario + "%").list();
    }
}
