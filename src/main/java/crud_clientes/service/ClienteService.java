package crud_clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crud_clientes.dto.ClienteDto;
import crud_clientes.entity.Cliente;
import crud_clientes.repository.IClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private IClienteRepository clienteRepository;
	
	//consulta de todos los clientes
	@Transactional(readOnly=true)
	public List<Cliente> findAll(){
		return (List<Cliente>)clienteRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public  Cliente findById(Long id) {
		return (Cliente) clienteRepository.findById(id).orElse(null);
	}
	
	//crear nuevo cliente
	
	public Cliente create(ClienteDto cliente) {

		Cliente clienteEntity = new Cliente();
		clienteEntity.setNombre(cliente.getNombre());
		clienteEntity.setApellido(cliente.getApellido());
		clienteEntity.setEmail(cliente.getEmail());
		
		return clienteRepository.save(clienteEntity);
	}
	
	
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}
	
	
	
	
	
}
