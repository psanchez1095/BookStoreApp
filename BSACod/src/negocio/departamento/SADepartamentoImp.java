package negocio.departamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import negocio.libreria.EntityLibreria;

public class SADepartamentoImp implements SADepartamento {

  @Override
  public Integer altaDepartamento(TransferDepartamento tDepartamento) throws Exception {
    Integer result = null;
    EntityManagerFactory entityManagerfactory = Persistence.createEntityManagerFactory("BSA_COD");
    EntityManager entityManager = entityManagerfactory.createEntityManager();
    EntityTransaction entityTransaction;

    entityTransaction = entityManager.getTransaction();

    entityTransaction.begin();

    try {
      Query namedQuery =
        entityManager.createNamedQuery("EntityDepartamento.findByNombre", EntityDepartamento.class);
      namedQuery.setParameter("nombre", tDepartamento.getNombre());

      EntityDepartamento departamentoTmp;

      try {
        departamentoTmp = (EntityDepartamento) namedQuery.getSingleResult();
      } catch (NoResultException e) {
        departamentoTmp = null;
      }

      if (departamentoTmp == null) {
        EntityLibreria libreria =
          entityManager.find(EntityLibreria.class, tDepartamento.getLibreria());
        if (libreria == null) {
          // entityTransaction.rollback();
          throw new Exception(
            "La librería asignada con id " + tDepartamento.getLibreria() + " no existe.");
          // result = -3;
        } else if (!libreria.getActivo()) {
          // entityTransaction.rollback();
          throw new Exception(
            "La librería asignada con id " + tDepartamento.getLibreria() + " no está activa.");
          // result = -3;
        } else {
          EntityDepartamento newDepartamento = new EntityDepartamento(tDepartamento, libreria);
          entityManager.persist(newDepartamento);
          entityTransaction.commit();
          TypedQuery<EntityDepartamento> query = entityManager.createQuery(
            "SELECT c FROM EntityDepartamento c WHERE c.nombre = :name", EntityDepartamento.class);
          TransferDepartamento t =
            query.setParameter("name", tDepartamento.getNombre()).getSingleResult().toTransfer();
          result = t.getId();
        }
      } else if (departamentoTmp != null && !departamentoTmp.getActivo()) {
        // entityTransaction.rollback();
        throw new Exception("El departamento ya existe pero no está activo.");
        // result = -5;
      } else {
        // entityTransaction.rollback();
        throw new Exception("El departamento ya existe.");
        // result = -10;
      }
    } catch (Exception e) {
      entityTransaction.rollback();
      if (e.getMessage().equalsIgnoreCase(
        "La librería asignada con id " + tDepartamento.getLibreria() + " no existe.")
        || e.getMessage().equalsIgnoreCase(
          "La librería asignada con id " + tDepartamento.getLibreria() + " no está activa.")
        || e.getMessage().equalsIgnoreCase("El departamento ya existe pero no está activo.")
        || e.getMessage().equalsIgnoreCase("El departamento ya existe."))
        throw e;
      else
        throw new Exception("Se ha producido un error al dar de alta el departamento.");
    } finally {
      entityManager.close();
      entityManagerfactory.close();
    }

    return result;
  }

  @Override
  public boolean bajaDepartamento(Integer idDepartamento) throws ClassNotFoundException, Exception {
    boolean result = false;
    EntityManagerFactory entityManagerfactory = Persistence.createEntityManagerFactory("BSA_COD");
    EntityManager entityManager = entityManagerfactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    transaction.begin();

    try {

      EntityDepartamento dpto = entityManager.find(EntityDepartamento.class, idDepartamento);

      if (dpto == null)
        throw new Exception("El departamento no existe, revisa el Id.");

      entityManager.lock(dpto, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
      dpto.setActivo(false);

      transaction.commit();
      
      result = true;
    } catch (Exception e) {
      transaction.rollback();
      if (e.getMessage().equalsIgnoreCase("El departamento no existe, revisa el Id."))
        throw e;
      else
        throw new Exception("Se ha producido un error al dar de baja el departamento.");
    } finally {
      entityManager.close();
      entityManagerfactory.close();
    }

    return result;
  }

  @Override
  public boolean modificarDepartamento(TransferDepartamento tDepartamento)
    throws ClassNotFoundException, Exception {
    boolean result = false;
    EntityManagerFactory entityManagerfactory = Persistence.createEntityManagerFactory("BSA_COD");
    EntityManager entityManager = entityManagerfactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    transaction.begin();

    try {

      EntityDepartamento auxDepartamento =
        entityManager.find(EntityDepartamento.class, tDepartamento.getId());

      if (auxDepartamento == null)
        throw new Exception("Departamento inexistente, revisa el ID.");

      entityManager.lock(auxDepartamento, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

      auxDepartamento.setNombre(tDepartamento.getNombre());
      auxDepartamento.setCantidadEmpleados(tDepartamento.getCantidadEmpleados());
      EntityLibreria libreria =
              entityManager.find(EntityLibreria.class, tDepartamento.getLibreria());
      auxDepartamento.setLibreria(libreria);
      transaction.commit();

      result = true;
    } catch (Exception e) {
      transaction.rollback();
      if (e.getMessage().equalsIgnoreCase("Departamento inexistente, revisa el ID."))
        throw e;
      else
        throw new Exception("Se ha producido un error al modificar el departamento.");
    } finally {
      entityManager.close();
      entityManagerfactory.close();
    }

    return result;

  }

  @Override
  public TransferDepartamento mostrarDepartamento(Integer idDepartamento)
    throws ClassNotFoundException, Exception {

    EntityManagerFactory entityManagerfactory = Persistence.createEntityManagerFactory("BSA_COD");
    EntityManager entityManager = entityManagerfactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();

    TransferDepartamento tDepartamento = null;
    try {

      EntityDepartamento dpto = entityManager.find(EntityDepartamento.class, idDepartamento);
      if (dpto == null)
        throw new Exception("El departamento no existe, revisa el Id.");

      tDepartamento = dpto.toTransfer();

      transaction.commit();

    } catch (Exception e) {
      transaction.rollback();
      if (e.getMessage().equalsIgnoreCase("El departamento no existe, revisa el Id."))
        throw e;
      else
        throw new Exception("Se ha producido un error al mostrar el departamento.");
    } finally {
      entityManager.close();
      entityManagerfactory.close();
    }

    return tDepartamento;
  }

  @Override
  public List<TransferDepartamento> listarDepartamentos() throws ClassNotFoundException, Exception {

    EntityManagerFactory entityManagerfactory = Persistence.createEntityManagerFactory("BSA_COD");
    EntityManager entityManager = entityManagerfactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

    entityTransaction.begin();

    List<TransferDepartamento> res = new ArrayList<TransferDepartamento>();
    try {
      TypedQuery<EntityDepartamento> query = entityManager
        .createNamedQuery("EntityDepartamento.findAllDepartamentos", EntityDepartamento.class);
      Collection<EntityDepartamento> resultEntity = query.getResultList();
      res = resultEntity.stream().map(entityDepartamento -> {
        return entityDepartamento.toTransfer();
      }).collect(Collectors.toList());
      entityTransaction.commit();
    } catch (Exception e) {
      entityTransaction.rollback();
       throw new Exception("Se ha producido un error al listar los departamentos.");
    } finally {
      entityManager.close();
      entityManagerfactory.close();
    }
    return res;
  }

}