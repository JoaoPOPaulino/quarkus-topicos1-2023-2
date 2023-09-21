package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Reserva;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {

    public List<Pagamento> findByReservaId(Reserva reservaId) {
        return find("reserva", reservaId).list();
    }

    // outros métodos úteis podem ser adicionados aqui
}
