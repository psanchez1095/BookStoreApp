package negocio.departamento;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import negocio.empleado.EntityEmpleado;
import negocio.libreria.EntityLibreria;
import negocio.material.EntityCantidad;

@NamedQueries({
  // @NamedQuery(name = "Departamento.findByid", query = "SELECT d FROM
  // Departamento d WHERE d.id = :id"),
  @NamedQuery(name = "EntityDepartamento.findByNombre", query = "SELECT d FROM EntityDepartamento d WHERE d.nombre = :nombre"),
  @NamedQuery(name = "EntityDepartamento.findAllDepartamentos", query = "select d from EntityDepartamento d")
 

})
@Entity
public class EntityDepartamento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer ID;
  
  @Version int VERSION = 0;

  private String nombre;
  private Integer cantidadEmpleados;
  private Boolean activo;

  @OneToMany(mappedBy = "dep")
  private Collection<EntityCantidad> material;

  @ManyToOne
  private EntityLibreria libreria;

  @OneToMany(mappedBy = "departamento")
  private Collection<EntityEmpleado> empleados;
  
  public EntityDepartamento(Integer IdDep) {
	    this.ID = IdDep;
	    
	  }
  public EntityDepartamento(TransferDepartamento t,EntityLibreria libreria) {
    this.ID = t.getId();
    this.cantidadEmpleados = t.getCantidadEmpleados();
    this.activo = t.getActivo();
    this.libreria = libreria;
    this.nombre = t.getNombre();
  }

  public EntityDepartamento() {
  }

  public Integer getID() {
    return this.ID;
  }

  public String getNombre() {
    return this.nombre;
  }

  public Integer getCantidadEmpleados() {
    return this.cantidadEmpleados;
  }

  public Boolean getActivo() {
    return this.activo;
  }

  public void setID(final Integer id) {
    this.ID = id;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  public void setCantidadEmpleados(final Integer cantidadEmpleados) {
    this.cantidadEmpleados = cantidadEmpleados;
  }

  public void setActivo(final Boolean activo) {
    this.activo = activo;
  }

  public Collection<EntityCantidad> getMaterial() {
    return material;
  }

  public void setMaterial(Collection<EntityCantidad> material) {
    this.material = material;
  }

  public EntityLibreria getLibreria() {
    return libreria;
  }

  public void setLibreria(EntityLibreria libreria) {
    this.libreria = libreria;
  }

  public Collection<EntityEmpleado> getEmpleados() {
    return empleados;
  }

  public void setEmpleados(Collection<EntityEmpleado> empleados) {
    this.empleados = empleados;
  }

  public TransferDepartamento toTransfer() {
    TransferDepartamento t = new TransferDepartamento();
    t.setActivo(this.activo);
    t.setCantidadEmpleados(this.cantidadEmpleados);
    t.setId(this.ID);
    t.setNombre(this.nombre);
    t.setLibreria(this.libreria.getId());
    return t;
  }

}