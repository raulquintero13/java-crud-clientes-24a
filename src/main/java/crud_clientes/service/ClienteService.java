package crud_clientes.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
		return (List<Cliente>)clienteRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
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
		clienteEntity.setCreateAt(new Date());
		
		return clienteRepository.save(clienteEntity);
	}

	
	@Transactional
	public Cliente createOrUpdate(ClienteDto cliente) {
		
		Cliente clienteEntity;
		if(cliente.getId()>0) {
			clienteEntity = new Cliente();
		}else {
			clienteEntity = clienteRepository.findById(cliente.getId()).orElse(null);
		}
		
		clienteEntity.setNombre(cliente.getNombre());
		clienteEntity.setApellido(cliente.getApellido());
		clienteEntity.setEmail(cliente.getEmail());
		
		
		return clienteRepository.save(clienteEntity);
	}
	
	
	@Transactional
	public Cliente update(ClienteDto cliente) {
		
		Cliente clienteEntity = clienteRepository.findById(cliente.getId()).orElse(null);
		
		clienteEntity.setNombre(cliente.getNombre());
		clienteEntity.setApellido(cliente.getApellido());
		clienteEntity.setEmail(cliente.getEmail());
		
		
		return clienteRepository.save(clienteEntity);
	}
	
	
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}
	
	
	
	
	
}
