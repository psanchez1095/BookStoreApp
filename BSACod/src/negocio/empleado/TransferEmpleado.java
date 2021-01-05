package negocio.empleado;

import negocio.departamento.EntityDepartamento;

public class TransferEmpleado {
//para relacionarse con la vista
	
	private Integer id;
	private String nombre;
	private String DNI;
	private String correo;
	private String cuentaBancaria;
	private Boolean activo;
	private EntityDepartamento departamento;
	
	public String getNombre() {
		return this.nombre;
	}

	public Integer getId() {
		return this.id;
	}


	public String getDNI() {
		return this.DNI;
	}

	
	public String getCuentaBancaria() {
		return this.cuentaBancaria;
	}
	
	public String getCorreo(){
		return this.correo;
	}

	public Boolean getActivo() {
		return this.activo;
	}
	
	public EntityDepartamento getDepartamento(){
		return this.departamento;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDNI(String dni) {
		this.DNI = dni;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	
	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public void setDepartamento(EntityDepartamento departamento){
		this.departamento = departamento;
	}
}
