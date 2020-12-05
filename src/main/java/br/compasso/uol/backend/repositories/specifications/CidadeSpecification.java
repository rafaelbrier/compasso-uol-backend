package br.compasso.uol.backend.repositories.specifications;

import br.compasso.uol.backend.dtos.CidadeFetchRequest;
import br.compasso.uol.backend.models.Cidade;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;
import java.util.stream.Stream;

public class CidadeSpecification implements Specification<Cidade> {

    private final transient CidadeFetchRequest cidadeFetchRequest;

    public CidadeSpecification(CidadeFetchRequest cidadeFetchRequest) {
        this.cidadeFetchRequest = cidadeFetchRequest;
    }

    @Override
    public Predicate toPredicate(Root<Cidade> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // Condição de Nome Igual
        Predicate nomePredicate = null;
        if (Objects.nonNull(cidadeFetchRequest.getNome())) {
            // Uma alternativa a String "nome" para maior automatização da busca do atributos e imunidade a renomeação de parâmetros
            // é gerar as classes de MetaDados das entidades
            nomePredicate = builder.equal(root.get("nome"), cidadeFetchRequest.getNome());
        }
        // Condição de Estado Igual
        Predicate estadoPredicate = null;
        if (Objects.nonNull(cidadeFetchRequest.getEstado())) {
            estadoPredicate = builder.equal(root.get("estado"), cidadeFetchRequest.getEstado());
        }
        // Combinação dos predicados em And
        Predicate[] nonNullPredicates = Stream.of(nomePredicate, estadoPredicate).filter(Objects::nonNull).toArray(Predicate[]::new);
        return builder.and(nonNullPredicates);
    }
}
