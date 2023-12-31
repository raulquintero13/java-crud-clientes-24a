package crud_clientes.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;

import crud_clientes.dto.ClienteDto;
import crud_clientes.entity.Cliente;
import crud_clientes.entity.TipoCliente;
import crud_clientes.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/clientes-todos")
	@ResponseStatus(HttpStatus.OK)
	public List< Cliente > todosClientes(){
		return clienteService.todosClientes();
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/clientes")
	@ResponseStatus(HttpStatus.OK)
	public List< Cliente > consulta(){
		return clienteService.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("clientes/{id}")
	public ResponseEntity<?> consultaPorId(@PathVariable Long id){
		
		
		Cliente cliente = null;
		String response="";
		
		try {
			cliente = clienteService.findById(id);
			
		}catch(DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente==null) {
			response ="El clientecon el ID: ".concat(id.toString()).concat(" no existe en base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> borraPorId(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente clienteDelete = this.clienteService.findById(id);
			if(clienteDelete==null) {
				response.put("mensaje", "Error al eliminar. El cliente no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			clienteService.delete(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		response.put("mensaje", "cliente eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody ClienteDto cliente){
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteNew = this.clienteService.create(cliente);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage().toString()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente creado con exito, con el ID"+clienteNew.getId());
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
		
	}
	
	@CrossOrigin
	@PutMapping("/clientes")
	public ResponseEntity<?> update(@RequestBody ClienteDto cliente){
		Cliente clienteNew = null;
//		TipoCliente tipoCliente = new TipoCliente();
//		tipoCliente.setId(3L);
//		cliente.setTipoCliente(tipoCliente);
		Map<String, Object> response = new HashMap<>();
		try {
			clienteNew = this.clienteService.update(cliente);
		}catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage().toString()));
			response.put("mensaje", "Error al tratar de actualizar el registo " + clienteNew.getId());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus. INTERNAL_SERVER_ERROR);
		}
	

		response.put("mensaje", "Cliente actualizado con exito, con el ID "+clienteNew.getId() +" " + cliente.toString() );
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	
	}
	
	
}
