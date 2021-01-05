package negocio.material;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EntityCantidadID implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer idDepartamento;
	
	private Integer idMaterial;	
	
	public EntityCantidadID(){}
	
	public EntityCantidadID(Integer idD, Integer idM){
		this.idDepartamento = idD;
		this.idMaterial = idM;
	}
	
	public int getDepartamento(){
		return this.idDepartamento;
	}
	
	public int getMaterial(){
		return this.idMaterial;
		}
	
	public boolean equals(Object o){
		return( (o instanceof EntityCantidadID)
				&&(this.idDepartamento.equals(((EntityCantidadID) o).getDepartamento()))
				&&(this.idMaterial.equals(((EntityCantidadID) o).getMaterial())));
	}
	
	public int hashCode(){
		return (idDepartamento.hashCode()+idMaterial.hashCode());
	}
	
	public void setDepartamento(Integer idD){
		this.idDepartamento = idD;
	}
	
	public void setMaterial(Integer idM){
		this.idMaterial = idM;
	}
	
}
