package br.compasso.uol.backend.repositories;

import br.compasso.uol.backend.models.Cliente;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
}
