package crud_clientes.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import crud_clientes.entity.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long>{

	
	
	@Query(
			  value = "SELECT * FROM clientes u  where u.id > 1", 
			  nativeQuery = true)
			Collection<Cliente> findAllActiveClientesNative();
	
	
	
}
