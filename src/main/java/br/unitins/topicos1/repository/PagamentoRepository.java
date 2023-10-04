package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepositoryBase<Pagamento, Long> {
    public List<Pagamento> findByTipoPagamento(Pagamento.TipoPagamento tipoPagamento) {
        return find("tipoPagamento", tipoPagamento).list();
    }

    public List<Pagamento> findByUsuario(Long usuarioId) {
        return list("usuario.id", usuarioId);
    }

}
