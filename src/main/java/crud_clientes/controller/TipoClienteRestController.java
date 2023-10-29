package crud_clientes.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import crud_clientes.dto.ClienteDto;
import crud_clientes.dto.TipoClienteDto;
import crud_clientes.entity.Cliente;
import crud_clientes.entity.TipoCliente;
import crud_clientes.service.TipoClienteService;

@RestController
@RequestMapping("/api")
public class TipoClienteRestController {

	@Autowired
	private TipoClienteService tipoClienteService;
	
	@CrossOrigin
	@GetMapping("/tipo-clientes")
	@ResponseStatus(HttpStatus.OK)
	public  List< TipoCliente > consulta(){
		return tipoClienteService.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/tipo-clientes/{id}")
	public ResponseEntity<?> consultaPorId(@PathVariable Long id){
		
		TipoCliente tipoCliente = null;
		String response = "";
		
		try {
			tipoCliente = tipoClienteService.findById(id);
		}catch(DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (tipoCliente ==null) {
			response = "Error al realizar la consulta.";
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<TipoCliente>(tipoCliente, HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@DeleteMapping("/tipo-clientes/{id}")
	public ResponseEntity<?> borraPorId(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		try {
			TipoCliente tipoClienteDelete = this.tipoClienteService.findById(id);
			if(tipoClienteDelete==null) {
				response.put("mensaje", "Error al eliminar. El cliente no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			tipoClienteService.delete(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		response.put("mensaje", "Tipo cliente eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	
	@CrossOrigin
	@PostMapping("/tipo-clientes")
	public ResponseEntity<?> create(@RequestBody TipoClienteDto tipoCliente){
		TipoCliente tipoClienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tipoClienteNew = this.tipoClienteService.create(tipoCliente);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage().toString()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Tipo Cliente creado con exito, con el ID" + tipoClienteNew.getId());
		response.put("cliente", tipoClienteNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
		
	}
	
	@CrossOrigin
	@PutMapping("/tipo-clientes")
	public ResponseEntity<?> update(@RequestBody TipoClienteDto tipoCliente){
		TipoCliente tipoClienteNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			tipoClienteNew = this.tipoClienteService.update(tipoCliente);
		}catch(DataAccessException e) {
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage().toString()));
			response.put("mensaje", "Error al tratar de actualizar el registo " + tipoClienteNew.getId());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus. INTERNAL_SERVER_ERROR);
		}
	

		response.put("mensaje", "Tipo Cliente actualizado con exito, con el ID "+tipoClienteNew.getId());
		response.put("cliente", tipoClienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	
	}

	
	
}
