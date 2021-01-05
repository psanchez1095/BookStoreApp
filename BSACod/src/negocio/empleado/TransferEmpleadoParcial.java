package negocio.empleado;

import negocio.departamento.EntityDepartamento;

public class TransferEmpleadoParcial extends TransferEmpleado{
	private Integer tiempoContrato;
	private Integer horasMensuales;
	private Double salarioPorHoras;
	
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
