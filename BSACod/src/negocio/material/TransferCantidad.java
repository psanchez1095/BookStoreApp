package negocio.material;

import negocio.departamento.TransferDepartamento;

public class TransferCantidad {
	
	protected TransferDepartamento dpto;
	protected TransferMaterial material;
	protected int stock;
	protected boolean activo;
	
	public TransferDepartamento getDpto() {
		return dpto;
	}
	public void setDpto(TransferDepartamento dpto) {
		this.dpto = dpto;
	}
	public TransferMaterial getMaterial() {
		return material;
	}
	public void setMaterial(TransferMaterial material) {
		this.material = material;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
