package negocio.empleado;

public class TransferEmpleadoCompleto extends TransferEmpleado{
	private Double salarioBase;
	private Double pagaExtra;
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
