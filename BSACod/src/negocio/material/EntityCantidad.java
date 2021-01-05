package negocio.material;

import java.util.Date;

import javax.persistence.*;
import negocio.departamento.EntityDepartamento;

@Entity
public class EntityCantidad {

@EmbeddedId private EntityCantidadID entRegId;
	

	@ManyToOne
	@MapsId("idDepartamento") private EntityDepartamento dep;

	@ManyToOne
	@ MapsId("idMaterial") private Material mat;
	
	private int stock;
	private boolean activo;
	
	public EntityCantidad(){}
	
	public EntityCantidad(EntityDepartamento entDep, Material entMat, int stock){
		this.dep = entDep;
		this.mat = entMat;
		this.stock = stock;
	}
	

	
	public EntityDepartamento getDep() {
		return dep;
	}

	public void setDepartamento(EntityDepartamento entDep) {
		this.dep = entDep;
	}
	
	public Material getMat() {
		return mat;
	}

	public void setMaterial(Material entMat) {
		this.mat = entMat;
	}
	

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean getActivo() {
		return this.activo;
	}
	
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public TransferCantidad toTransfer() {
		TransferCantidad t = new TransferCantidad();
		t.setActivo(this.activo);
		t.setStock(this.stock);
		t.setDpto(this.dep.toTransfer());
		t.setMaterial(this.mat.toTransfer());
		return t;
	}
	
}
