package br.compasso.uol.backend.repositories.specifications;

import br.compasso.uol.backend.dtos.ClienteFetchRequest;
import br.compasso.uol.backend.models.Cliente;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class ClienteSpecification implements Specification<Cliente> {

    private final transient ClienteFetchRequest clienteFetchRequest;

    public ClienteSpecification(ClienteFetchRequest clienteFetchRequest) {
        this.clienteFetchRequest = clienteFetchRequest;
    }

    @Override
    public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // Condição de Nome Igual
        if (Objects.nonNull(clienteFetchRequest.getNome())) {
            // Uma alternativa a String "nome" para maior automatização da busca do atributos e imunidade a renomeação de parâmetros
            // é gerar as classes de MetaDados das entidades
            return builder.equal(root.get("nome"), clienteFetchRequest.getNome());
        }
        return null;
    }
}
