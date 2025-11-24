package grupo.concessionaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import grupo.concessionaria.entities.Vendedor;


@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

}
