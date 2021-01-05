/**
 * 
 */
package negocio.empleado;

import java.util.List;


public interface SAEmpleado {

	public Integer altaEmpleado(TransferEmpleado empleado) throws Exception;
	public Integer bajaEmpleado(Integer idEmpleado) throws Exception;
	public EntityEmpleado mostrarEmpleado(Integer idEmpleado) throws Exception; 
	public Integer modificarEmpleado(TransferEmpleado empleado) throws Exception; 
	public List<EntityEmpleado> listarEmpleado() throws Exception; 
	public Double totalSalariosDepartamento(Integer idDepartamento);
}