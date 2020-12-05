package br.compasso.uol.backend.repositories;

import br.compasso.uol.backend.models.Cidade;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CidadeRepository extends CrudRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {
}
