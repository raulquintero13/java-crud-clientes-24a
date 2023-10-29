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

import crud_clientes.dto.TipoClienteDto;
import crud_clientes.entity.TipoCliente;
import crud_clientes.repository.ITipoClienteRepository;


@Service
public class TipoClienteService {

	@Autowired
	private ITipoClienteRepository tipoClienteRepository;
	
	//consulta de todos los clientes
	@Transactional(readOnly=true)
	public List<TipoCliente> findAll(){
		return (List<TipoCliente>)tipoClienteRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	@Transactional(readOnly=true)
	public  TipoCliente findById(Long id) {
		return (TipoCliente) tipoClienteRepository.findById(id).orElse(null);
	}
	
	public  TipoCliente create(TipoClienteDto tipoCliente) {
		
		TipoCliente tipoClienteEntity = new TipoCliente();
		tipoClienteEntity.setTipoCliente( tipoCliente.getTipoCliente());
		
		return tipoClienteRepository.save(tipoClienteEntity);
		
	}
	
	
	public TipoCliente update(TipoClienteDto tipoClienteDto) {
		
		TipoCliente tipoClienteEntity = tipoClienteRepository.findById(tipoClienteDto.getId()).orElse(null);
		tipoClienteEntity.setTipoCliente(tipoClienteDto.getTipoCliente());

		return tipoClienteRepository.save(tipoClienteEntity);
	}
	
	
	public void delete(Long id) {
		tipoClienteRepository.deleteById(id);
	}
	
	
}
