package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Pagamento;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepositoryBase<Pagamento, Long> {
    public List<Pagamento> findByTipoPagamento(Pagamento.TipoPagamento tipoPagamento) {
        return find("tipoPagamento", tipoPagamento).list();
    }

}
