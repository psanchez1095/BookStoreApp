/**
 * 
 */
package negocio.empleado;

import javax.persistence.*;

import negocio.departamento.EntityDepartamento;;

@Entity
//Queries //TODO

public class EntityEmpleadoTiempoCompleto extends EntityEmpleado {
	
	@Version int Version = 0;

	private Double salarioBase;
	private Double pagaExtra;
	
	public EntityEmpleadoTiempoCompleto(){
		
	}
	
	public EntityEmpleadoTiempoCompleto(TransferEmpleado t,Double salarioBase,Double pagaExtra){
		super(t.getId(),t.getNombre(),t.getDNI(), t.getCorreo(), t.getCuentaBancaria(), t.getActivo(),t.getDepartamento());
		this.salarioBase = salarioBase;
		this.pagaExtra = pagaExtra;
	}
	
	//TODO
	public EntityEmpleadoTiempoCompleto(String nombre, String dni, String correo, String cuentaBancaria, Boolean activo, 
			EntityDepartamento departamento, Double salarioBase, Double pagaExtra){
		super(nombre, dni, correo, cuentaBancaria, activo, departamento);
		this.salarioBase = salarioBase;
		this.pagaExtra = pagaExtra;
	}
	
	public Double getSalarioBase() {
		return this.salarioBase;
	}

	public Double getPagaExtra() {
		return this.pagaExtra;
	}

	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public void setPagaExtra(Double pagaExtra) {
		this.pagaExtra = pagaExtra;
	}

}