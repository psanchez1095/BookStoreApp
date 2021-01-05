/**
 * 
 */
package negocio.empleado;

import javax.persistence.Entity;
import javax.persistence.Version;

import negocio.departamento.EntityDepartamento;

@Entity
//Queries //TODO

public class EntityEmpleadoTiempoParcial extends EntityEmpleado {
	
	@Version int Version = 0;

	private Integer tiempoContrato;
	private Integer horasMensuales;
	private Double salarioPorHoras;
	
	public EntityEmpleadoTiempoParcial(){
		
	}
	
	public EntityEmpleadoTiempoParcial(TransferEmpleado empleado,Integer tiempoContrato, Integer horasMensuales, Double salarioPorHoras){
		super(empleado.getId(), empleado.getNombre(),empleado.getDNI(), empleado.getCorreo(), empleado.getCuentaBancaria(), empleado.getActivo(),empleado.getDepartamento());
		this.tiempoContrato = tiempoContrato;
		this.horasMensuales = horasMensuales;
		this.salarioPorHoras = salarioPorHoras;
	}
	public EntityEmpleadoTiempoParcial(String nombre, String dni, String correo, String cuentaBancaria, Boolean activo, 
			EntityDepartamento departamento, Integer tiempoContrato, Integer horasMensuales, Double salarioPorHoras){
		super(nombre, dni, correo, cuentaBancaria, activo, departamento);
		this.tiempoContrato = tiempoContrato;
		this.horasMensuales = horasMensuales;
		this.salarioPorHoras = salarioPorHoras;
	}


	public Integer getTiempoContrato() {
		return this.tiempoContrato;
	}

	public Integer getHorasMensuales() {
		return this.horasMensuales;
	}

	public Double getSalarioPorHoras() {
		return this.salarioPorHoras;
	}

	public void setTiempoContrato(Integer tiempoContrato) {
		this.tiempoContrato = tiempoContrato;
	}

	public void setHorasMensuales(Integer horasMensuales) {
		this.horasMensuales = horasMensuales;
	}

	public void setSalarioPorHoras(Double salarioPorHoras) {
		this.salarioPorHoras = salarioPorHoras;
	}
}