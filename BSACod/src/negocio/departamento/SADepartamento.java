/**
 * 
 */
package negocio.departamento;

import java.util.Collection;


/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author termo
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface SADepartamento {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer altaDepartamento(TransferDepartamento departamento) throws Exception;
	public boolean bajaDepartamento(Integer idDepartamento) throws ClassNotFoundException, Exception;
	public boolean modificarDepartamento(TransferDepartamento departamento) throws ClassNotFoundException, Exception;
	public Collection<TransferDepartamento> listarDepartamentos() throws ClassNotFoundException, Exception;
	public TransferDepartamento mostrarDepartamento(Integer idDepartamento) throws ClassNotFoundException, Exception;
}