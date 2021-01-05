package negocio.libreria;

public class TransferLibreria {
	private Integer id;
	private String nombre;
	private String direccion;
	private Boolean activo;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(final Integer id){
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(final String direccion) {
		this.direccion = direccion;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(final boolean activo) {
		this.activo = activo;
	}
	
}
