package crud_clientes.dto;

import java.util.List;

import crud_clientes.entity.Cliente;

public class TipoClienteDto {

	private Long id;
	
	private String tipoCliente;
	
	private List<Cliente> clientes;
	
	
//	public List<Cliente> getClientes() {
//		return clientes;
//	}
//	public void setClientes(List<Cliente> clientes) {
//		this.clientes = clientes;
//	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	@Override
	public String toString() {
		return "TipoClienteDto [id=" + id + ", tipoCliente=" + tipoCliente + "]";
	}
	
	
	


}
