package negocio.material;

import java.util.Collection;

import javax.persistence.*;
//import jakarta.persistence.Version;
//import java.util.Collection;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import negocio.departamento.EntityDepartamento;

import negocio.departamento.EntityDepartamento;

@Entity
@NamedQueries({
	  // @NamedQuery(name = "Material.findByid", query = "SELECT d FROM
	  // Departamento d WHERE d.id = :id"),
	  @NamedQuery(name = "Material.findByTipo", query = "SELECT m FROM Material m WHERE m.tipo = :tipo"),
	  @NamedQuery(name = "Material.findAllMateriales", query = "select m from Material m")

	})
public class Material {
	
	@Version int Version = 0;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String tipo;
	private Boolean activo;
	
	@OneToMany(mappedBy="mat")
	private Collection<EntityCantidad> departamentos;
	
	public Material(){}
	
	public Material(TransferMaterial tMaterial){
		this.id = tMaterial.getId();
		this.tipo = tMaterial.getTipo();
		this.activo = tMaterial.getActivo();
	}


	public Integer getId() {
		return this.id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	TransferMaterial toTransfer(){
		TransferMaterial t = new TransferMaterial();
		t.setId(this.id);
		t.setTipo(this.tipo);
		t.setActivo(this.activo);
		return t;
	}
	
}