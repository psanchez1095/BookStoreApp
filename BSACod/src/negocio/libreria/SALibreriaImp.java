package negocio.libreria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.LockModeType;
//import jakarta.persistence.NoResultException;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.Query;
//import jakarta.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SALibreriaImp implements SALibreria {

	public static final String SELECT_ALL_QUERY = "SELECT * FROM libreria";

	public Integer altaLibreria(TransferLibreria tLibreria) throws Exception {
		int result = -1;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			TypedQuery<EntityLibreria> namedQuery = em.createNamedQuery("Libreria.findByNombre", EntityLibreria.class).setParameter("nombre", tLibreria.getNombre());
			EntityLibreria auxLibreria;
			try {
				auxLibreria = namedQuery.getSingleResult();
			} catch (NoResultException e) {
				auxLibreria = null;
			}
			if (auxLibreria == null) {
				auxLibreria = new EntityLibreria();
				auxLibreria.setNombre(tLibreria.getNombre());
				auxLibreria.setDireccion(tLibreria.getDireccion());
				auxLibreria.setActivo(tLibreria.getActivo());
				em.persist(auxLibreria);
				transaction.commit();
				result = em.createNamedQuery("Libreria.findByNombre", EntityLibreria.class).setParameter("nombre", tLibreria.getNombre()).getSingleResult().getId();
			} else if (auxLibreria.getActivo()) {
				transaction.rollback();
				result = -5;
			} else {
				transaction.rollback();
				result = -10;
			}
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			em.close();
			emf.close();
		}
		return result;
	}

	public void bajaLibreria(Integer idLibreria) throws ClassNotFoundException, Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {
			EntityLibreria libreria = em.find(EntityLibreria.class, idLibreria);
			if (libreria == null) {
				throw new Exception("Libreria inexistente, revisa el Id.");
			}
			em.lock(libreria, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			libreria.setActivo(false);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

	public Integer modificarLibreria(TransferLibreria tLibreria) throws ClassNotFoundException, Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {
			EntityLibreria auxLibreria = em.find(EntityLibreria.class, tLibreria.getId());

			if (auxLibreria == null)
				throw new Exception("Libreria inexistente, revisa el ID.");

			em.lock(auxLibreria, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

			auxLibreria.setNombre(tLibreria.getNombre());
			auxLibreria.setDireccion(tLibreria.getDireccion());

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			em.close();
			emf.close();
		}

		return tLibreria.getId();
	}

	public TransferLibreria mostrarLibreria(Integer idLibreria) throws ClassNotFoundException, Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		TransferLibreria tLibreria = null;

			EntityLibreria libreria = em.find(EntityLibreria.class, idLibreria);
			if (libreria == null) {
				transaction.rollback();
				throw new Exception("La libreria no existe, revisa el ID.");
			}
			tLibreria = libreria.toTransfer();
		
			em.close();
			emf.close();
		

		return tLibreria;
	}

	public Collection<TransferLibreria> listarLibrerias() throws ClassNotFoundException, Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BSA_COD");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		Collection<TransferLibreria> res = new ArrayList<TransferLibreria>();
		
		try {
			List<EntityLibreria> list = em.createNamedQuery("Libreria.findAll", EntityLibreria.class).getResultList();
			for (EntityLibreria entity : list) {
				res.add(entity.toTransfer());
			}
		} finally {
			em.close();
			emf.close();
		}
		return res;
	}
}