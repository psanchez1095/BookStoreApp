/**
 * 
 */
package negocio.empleado;

import java.util.List;

import javax.persistence.*;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.LockModeType;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.Query;
import negocio.departamento.EntityDepartamento;

public class SAEmpleadoImp implements SAEmpleado {


	public Integer altaEmpleado(TransferEmpleado empleado) throws Exception {
		int respuesta = -1;
		EntityManagerFactory emf;
		EntityManager em;
		EntityTransaction transaction;
		
		
			emf = Persistence.createEntityManagerFactory("BSA_COD");
			em = emf.createEntityManager();
			transaction = em.getTransaction();
			
			transaction.begin();
			
			
			Query query = em.createNamedQuery("negocio.empleado.Empleado.findBydni", EntityEmpleado.class);
			query.setParameter("dni", empleado.getDNI());

			 
			
			EntityDepartamento dep = em.find(EntityDepartamento.class, empleado.getDepartamento().getID());
			EntityEmpleado empleadoYaExistente = null;
			
			
			List<EntityEmpleado> resultList = query.getResultList();
			if (!resultList.isEmpty()) {
				empleadoYaExistente = (EntityEmpleado) resultList.get(0);
			}
			
			if (dep == null || !dep.getActivo()){
				transaction.rollback();
				throw new Exception ("El departamento asignado no existe.");
			}
			else if (empleadoYaExistente == null) {
				
				//dep.asignarEmpleado(empleado);//TODO en la clase departamento debería crearse un método para asignar empleado a dep
				if(empleado instanceof TransferEmpleadoCompleto){
				EntityEmpleadoTiempoCompleto newEmpleado = new EntityEmpleadoTiempoCompleto(empleado,((TransferEmpleadoCompleto) empleado).getSalarioBase(),((TransferEmpleadoCompleto) empleado).getPagaExtra());
				em.persist(newEmpleado);
				transaction.commit();
				respuesta = newEmpleado.getId();
				}
				else{
					EntityEmpleadoTiempoParcial newEmpleado = new EntityEmpleadoTiempoParcial(empleado,((TransferEmpleadoParcial) empleado).getTiempoContrato(),((TransferEmpleadoParcial) empleado).getHorasMensuales(),((TransferEmpleadoParcial) empleado).getSalarioPorHoras());
					em.persist(newEmpleado);
					transaction.commit();
					respuesta = newEmpleado.getId();
				}
				
				
			}
			else if (empleadoYaExistente.getActivo()) {
				transaction.rollback();
				throw new Exception ("El empleado ya existe.");
			}
			
			 else {
				EntityDepartamento depBBDD = empleadoYaExistente.getDepartamento();
				
				empleadoYaExistente.setNombre(empleado.getNombre());
				empleadoYaExistente.setCorreo(empleado.getCorreo());
				empleadoYaExistente.setCuentaBancaria(empleado.getCuentaBancaria());
				
				if (empleadoYaExistente instanceof EntityEmpleadoTiempoCompleto) {
					Double pagaExtra = ((TransferEmpleadoCompleto) empleado).getPagaExtra();
					Double salarioBase = ((TransferEmpleadoCompleto) empleado).getSalarioBase();
					
					((EntityEmpleadoTiempoCompleto) empleadoYaExistente).setPagaExtra(pagaExtra);
					((EntityEmpleadoTiempoCompleto) empleadoYaExistente).setSalarioBase(salarioBase);;
				} else if (empleadoYaExistente instanceof EntityEmpleadoTiempoParcial) {
					Integer horasMensuales = ((TransferEmpleadoParcial) empleado).getHorasMensuales();
					Double salarioPorHoras = ((TransferEmpleadoParcial) empleado).getSalarioPorHoras();
					
					((EntityEmpleadoTiempoParcial) empleadoYaExistente).setHorasMensuales(horasMensuales);;
					((EntityEmpleadoTiempoParcial) empleadoYaExistente).setSalarioPorHoras(salarioPorHoras);
				}
		
				if (dep.getID() != depBBDD.getID()) {
					//dep.asignarEmpleado(empleadoYaExistente); //TODO tiene que crearse en Departamento
					//departamentoBBDD.desasignarEmpleado(empleadoYaExistente);
					empleadoYaExistente.setDepartamento(dep);
				} else {
					//depBBDD.aumentarNumeroDeEmpleadosActivos(); //TODO tiene que crearse en Departamento 
				}
				
				empleadoYaExistente.setActivo(true);
				transaction.commit();
				respuesta = 0;
			}
			
			em.close();
			emf.close();
			
			return respuesta;
		}

	
	@Override
	public Integer bajaEmpleado(Integer idEmpleado)throws Exception {
		
		int respuesta = -1;
		EntityManagerFactory emf;
		EntityManager em;
		EntityTransaction transaction;

	
			emf = Persistence.createEntityManagerFactory("BSA_COD");
			em = emf.createEntityManager();
			transaction = em.getTransaction();

			transaction.begin();
			
			EntityEmpleado empleado = em.find(EntityEmpleado.class, idEmpleado);
			
			if (empleado == null) {
				transaction.rollback();
				throw new Exception ("El empleado no existe.");
			} else if (!empleado.getActivo()) {
				transaction.rollback();
				throw new Exception ("El empleado no está activo.");
			} else {
				EntityDepartamento departamento = empleado.getDepartamento();
				//departamento.reducirNumeroDeEmpleadosActivos();//TODO Hay que crearlo en departamento 
				empleado.setActivo(false);
				
				transaction.commit();
				respuesta = 0;
			}
			
			em.close();
			emf.close();
	

		return respuesta;
	}
	
	
	public Integer modificarEmpleado(TransferEmpleado empleadoIn)throws Exception {
		
		int respuesta = -1;
		EntityManagerFactory emf;
		EntityManager em;
		EntityTransaction transaction = null;
		emf = Persistence.createEntityManagerFactory("BSA_COD");
		em = emf.createEntityManager();
		transaction = em.getTransaction();

		transaction.begin();
		
		EntityEmpleado empleadoYaExistente = null;
		EntityEmpleado empleado = em.find(EntityEmpleado.class, empleadoIn.getId());
		
		Query query = em.createNamedQuery("negocio.empleado.Empleado.findBydni", EntityEmpleado.class);
		query.setParameter("dni", empleadoIn.getDNI());
		List<EntityEmpleado> resultList = query.getResultList();
		
		if (!resultList.isEmpty()) {
			empleadoYaExistente = (EntityEmpleado) resultList.get(0);
		}
		
		EntityDepartamento departamento = em.find(EntityDepartamento.class, empleadoIn.getDepartamento().getID(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		
		if (empleado == null) {
			transaction.rollback();
			throw new Exception ("El empleado no existe.");
			
		} else if (empleadoYaExistente != null && empleadoYaExistente.getId() != empleado.getId()) {
			transaction.rollback();
			throw new Exception ("El empleado es distinto");
		}  else if (departamento == null) {
			transaction.rollback();
			throw new Exception ("El departamento no existe.");
		} else if (!departamento.getActivo()) {
			transaction.rollback();
			throw new Exception ("El departamento no está activo.");
		} else {
			EntityDepartamento departamentoAntiguo = empleado.getDepartamento();
			
			if (departamento.getID() != departamentoAntiguo.getID()) {
				//departamentoAntiguo.desasignarEmpleado(empleado); //TODO Hay que crearlo en departamento 
				//departamento.asignarEmpleado(empleado); //TODO Hay que crearlo en departamento 
				empleado.setDepartamento(departamento);
			} else if (!empleado.getActivo()) {
				//departamentoAntiguo.aumentarNumeroDeEmpleadosActivos(); //TODO Hay que crearlo en departamento 
			}
			
			if (!empleado.getActivo()) {
				empleado.setActivo(true);
				respuesta = 0;
			}
			
			empleado.setNombre(empleadoIn.getNombre());
			empleado.setCorreo(empleadoIn.getCorreo());
			empleado.setCuentaBancaria(empleadoIn.getCuentaBancaria());
			empleado.setDNI(empleadoIn.getDNI());
			
			if (empleado instanceof EntityEmpleadoTiempoCompleto) {
				((EntityEmpleadoTiempoCompleto) empleado).setSalarioBase((((TransferEmpleadoCompleto) empleadoIn).getSalarioBase()));
				((EntityEmpleadoTiempoCompleto) empleado).setPagaExtra((((TransferEmpleadoCompleto) empleadoIn).getPagaExtra()));
			} else if (empleado instanceof EntityEmpleadoTiempoParcial) {
				((EntityEmpleadoTiempoParcial) empleado).setTiempoContrato((((TransferEmpleadoParcial) empleadoIn).getTiempoContrato()));
				((EntityEmpleadoTiempoParcial) empleado).setHorasMensuales((((TransferEmpleadoParcial) empleadoIn).getHorasMensuales()));
			}
			
			transaction.commit();
		}
		
		em.close();
		emf.close();
		return respuesta;
	}

	public EntityEmpleado mostrarEmpleado(Integer idEmpleado)throws Exception {
		
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		/*if (transaction == null){
			throw new Exception ("Error en la conexión con la BBDD");
		}*/
		transaction.begin();
		
		EntityEmpleado empleado = em.find(EntityEmpleado.class, idEmpleado);
		
		if (empleado == null) {
			transaction.rollback();
			throw new Exception ("El empleado no existe.");}
		
		em.close();
		emf.close();
		
		return empleado;
		}
	

	public List<EntityEmpleado> listarEmpleado() throws Exception {
	
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("BSA_COD");
			EntityManager em = emf.createEntityManager();
			Query query = em.createNamedQuery("negocio.empleado.Empleado.findAllEmployees", EntityEmpleado.class);
			
			List<EntityEmpleado> empleados = query.getResultList();
			
			
			
			em.close();
			emf.close();
			
			return empleados;
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la BBDD");
		}
	
	}
	
	//TODO falta añadir clases en departamento
	public Double totalSalariosDepartamento(Integer idDepartamento) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager em = emf.createEntityManager();
		EntityDepartamento dep = em.find(EntityDepartamento.class, idDepartamento);
		return null;
		
	}






}