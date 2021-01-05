package negocio.libreria;

import java.util.Collection;
import java.util.Set;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.NamedQuery;
//import jakarta.persistence.OneToMany;
//import negocio.departamento.EntityDepartamento;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import negocio.departamento.EntityDepartamento;

@Entity
@NamedQuery(name = "Libreria.findByNombre", query = "SELECT l FROM EntityLibreria l WHERE l.nombre = :nombre")
@NamedQuery(name = "Libreria.findAll", query = "SELECT l FROM EntityLibreria l")

public class EntityLibreria {
	
	@Version int Version = 0;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String direccion;
	private Boolean activo;
	
	@OneToMany(mappedBy = "libreria")
	private Collection<EntityDepartamento> departamento;

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
	
	public Collection<EntityDepartamento> getDepartamento(){
		return this.departamento;
	}
	
	public void setDepartamento(Collection<EntityDepartamento> departamento){
		this.departamento = departamento;
	}
	
	public TransferLibreria toTransfer(){
		TransferLibreria tL = new TransferLibreria();
		
		tL.setActivo(this.activo);
		tL.setNombre(this.nombre);
		tL.setDireccion(this.direccion);
		tL.setId(this.id);
		
		return tL;
	}
}