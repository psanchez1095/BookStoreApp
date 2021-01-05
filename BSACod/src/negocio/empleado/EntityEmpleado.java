/**
 * 
 */
package negocio.empleado;


import java.util.Collection;

import javax.persistence.*;
//import jakarta.persistence.Inheritance;
//import jakarta.persistence.InheritanceType;
//import jakarta.persistence.NamedQueries;
//import jakarta.persistence.NamedQuery;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
import negocio.departamento.EntityDepartamento;

@Inheritance(strategy = InheritanceType.JOINED) //TODO
@Entity
@NamedQueries({ //TODO
	@NamedQuery(name = "negocio.empleado.Empleado.findByid", query = "select obj from EntityEmpleado obj where obj.id = :id"),
	@NamedQuery(name = "negocio.empleado.Empleado.findBydni", query = "select obj from EntityEmpleado obj where obj.DNI = :dni"),
	@NamedQuery(name = "negocio.empleado.Empleado.findAllEmployees", query = "select obj from EntityEmpleado obj"),
	
})
public class EntityEmpleado {
	
	@Version int Version = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;

	private String nombre;
	private String DNI;
	private String correo;
	private String cuentaBancaria;
	private Boolean activo;
	
	
	
	@ManyToOne
	private EntityDepartamento departamento;
	
	
	
	public EntityEmpleado(Integer id, String nombre, String dni, String correo, String cuentaBancaria, Boolean activo, 
			EntityDepartamento departamento){ //TODO
		this.id = id;
		this.nombre = nombre;
		this.DNI = dni;
		this.correo = correo;
		this.cuentaBancaria = cuentaBancaria;
		this.activo = activo;
		this.departamento = departamento;
	}
	
	public EntityEmpleado(String nombre, String dni, String correo, String cuentaBancaria, Boolean activo, 
			EntityDepartamento departamento){ //TODO
		this.nombre = nombre;
		this.DNI = dni;
		this.correo = correo;
		this.cuentaBancaria = cuentaBancaria;
		this.activo = activo;
		this.departamento = departamento;
	}
	

	public EntityEmpleado() {
		
	}

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