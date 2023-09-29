package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepositoryBase<Endereco, Long> {
    public List<Endereco> findByCidade(String cidade) {
        return find("cidade", cidade).list();
    }

    public List<Endereco> findByEstado(String estado) {
        return find("estado", estado).list();
    }
}
