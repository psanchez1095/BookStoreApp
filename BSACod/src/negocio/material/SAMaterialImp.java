package negocio.material;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.persistence.*;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.LockModeType;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.Query;
//import jakarta.persistence.TypedQuery;

public class SAMaterialImp implements SAMaterial {
	public static final String SELECT_ALL_QUERY = "SELECT m FROM material m";

	public Integer altaMaterial(TransferMaterial transferMaterial) throws Exception {
		EntityManagerFactory entityManagerfactory = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager entityManager = entityManagerfactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {

			Query namedQuery = entityManager.createNamedQuery("Material.findByTipo", Material.class);
			namedQuery.setParameter("tipo", transferMaterial.getTipo());

			Material materialTmp = (Material) namedQuery.getSingleResult();

				if (materialTmp.getActivo() == false) {
					materialTmp.setActivo(true);
				} else {
					throw new Exception("El material introducido ya existe y tiene estado activo");
				}
			transaction.commit();

		}
		catch(NoResultException correcto){
			Material newMaterial = new Material(transferMaterial);
			entityManager.persist(newMaterial);
			transaction.commit();
		}
		catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerfactory.close();
		}
		return transferMaterial.getId();
	}

	public void bajaMaterial(Integer idMaterial) throws Exception {
		EntityManagerFactory entityManagerfactory = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager entityManager = entityManagerfactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// Integer idResultado = -1;
		try {
			Material material = entityManager.find(Material.class, idMaterial);
			if (material == null) {
				throw new Exception("Material inexistente, revisa el Id.");
			}
			entityManager.lock(material, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			material.setActivo(false);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerfactory.close();
		}
		// return idResultado;
	}

	public Integer modificarMaterial(TransferMaterial transferMaterial) throws Exception {
		EntityManagerFactory entityManagerfactory = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager entityManager = entityManagerfactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		try {
			Material auxMaterial = entityManager.find(Material.class, transferMaterial.getId());
			if (auxMaterial == null) {
				throw new Exception("Material inexistente, revisa el ID.");
			}
			entityManager.lock(auxMaterial, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			auxMaterial.setTipo(transferMaterial.getTipo());
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerfactory.close();
		}
		return transferMaterial.getId();
	}

	public Collection<TransferMaterial> listarMaterial() throws Exception {
		EntityManagerFactory entityManagerfactory = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager entityManager = entityManagerfactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Collection<TransferMaterial> res = new ArrayList<TransferMaterial>();

		try {
			TypedQuery<Material> query = entityManager.createNamedQuery("Material.findAllMateriales", Material.class);
			Collection<Material> resultEntity = query.getResultList();
			res = resultEntity.stream().map(material -> {
				return material.toTransfer();
			}).collect(Collectors.toList());
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerfactory.close();
		}

		
		return res;
	}

	public TransferMaterial mostrarMaterial(Integer id) throws Exception {
		EntityManagerFactory entityManagerfactory = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager entityManager = entityManagerfactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TransferMaterial transferMaterial = null;
		try {
			Material material = entityManager.find(Material.class, id);
			if (material == null) {
				throw new Exception("El material no existe");
			}
			transferMaterial = material.toTransfer();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerfactory.close();
		}

		return transferMaterial;
	}
}