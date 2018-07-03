package persistence.security;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.RecordStatus;
import model.security.User;
import persistence.OpasPersistenceConstants;
import persistence.OpasPersistenceException;

public class UserDaoHibernateImpl implements UserDaoIface {

	@Override
	public User select(Long userId) throws OpasPersistenceException {

		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();

		User user = select(entityManager, userId);

		entityManager.close();

		return user;
	}

	@Override
	public Long insert(User user) throws OpasPersistenceException {

		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Long userId = insert(entityManager, user);

		entityManager.getTransaction().commit();
		entityManager.close();

		return userId;
	}

	@Override
	public void update(User user) throws OpasPersistenceException {

		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		update(entityManager, user);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void delete(User user) throws OpasPersistenceException {
		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		delete(entityManager, user);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	private User select(EntityManager entityManager, Long userId) throws OpasPersistenceException {
		User user = entityManager.find(User.class, userId);
		return user;
	}

	private Long insert(EntityManager entityManager, User user) throws OpasPersistenceException {
		entityManager.persist(user);
		return user.getUserId();
	}

	private void update(EntityManager entityManager, User user) throws OpasPersistenceException {
		entityManager.merge(user);
	}

	private void delete(EntityManager entityManager, User user) throws OpasPersistenceException {

		user.setRecordStatus(RecordStatus.INACTIVE);
		entityManager.merge(user);
	}

}
