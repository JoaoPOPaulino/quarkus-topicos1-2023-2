package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Pagamento;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepositoryBase<Pagamento, Long> {

}
