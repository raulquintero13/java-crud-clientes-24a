package crud_clientes.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name="tipo_cliente")
public class TipoCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="tipo_cliente")
	private String tipoCliente;
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCliente", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Cliente> clientes;
	
	
	
	public TipoCliente() {
		super();
	}
	public TipoCliente(String tipoCliente) {
		super();
		this.tipoCliente = tipoCliente;
	}
	
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
		return "TipoCliente [id=" + id + ", tipoCliente=" + tipoCliente ;
	}


}
