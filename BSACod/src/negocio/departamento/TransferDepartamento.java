package negocio.departamento;

public class TransferDepartamento {
	private Integer id;
	private String nombre;
	private Integer cantidadEmpleados;
  private Integer libreria;
	private Boolean activo;
	
	public Integer getCantidadEmpleados() {
		return cantidadEmpleados;
	}

	public void setCantidadEmpleados(Integer cantidadEmpleados) {
		this.cantidadEmpleados = cantidadEmpleados;
	}

	public Integer getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nom) {
		this.nombre = nom;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

  public Integer getLibreria() {
    return libreria;
  }

  public void setLibreria(Integer libreria) {
    this.libreria = libreria;
  }

}
