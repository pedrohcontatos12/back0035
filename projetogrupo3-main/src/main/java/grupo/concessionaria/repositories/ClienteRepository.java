package grupo.concessionaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grupo.concessionaria.entities.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
