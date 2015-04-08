package br.com.malaDiretaService.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public final class JPAUtil {

	private static EntityManager entityManager;

	private JPAUtil() {

	}

	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = createEntityManager();
		}
		return entityManager;
	}

	private static EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MALADIRETA_PU");
		return emf.createEntityManager();
	}

	public static EntityTransaction getTransaction() {
		return getEntityManager().getTransaction();
	}

	public static void beginTransaction() {
		if (!getTransaction().isActive()) {
			getTransaction().begin();
		}
	}

	public static void commitTransaction() {
		if (getTransaction().isActive()) {
			getTransaction().commit();
		}
	}

	public static void rollbackTransaction() {
		if (getTransaction().isActive()) {
			getTransaction().rollback();
		}
	}
}
