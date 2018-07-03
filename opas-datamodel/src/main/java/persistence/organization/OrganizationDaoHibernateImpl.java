package persistence.organization;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.RecordStatus;
import model.organization.Organization;
import persistence.OpasPersistenceConstants;
import persistence.OpasPersistenceException;

public class OrganizationDaoHibernateImpl implements OrganizationDaoIface {

	@Override
	public Organization select(Long organizationId) throws OpasPersistenceException {

		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();

		Organization Organization = select(entityManager, organizationId);

		entityManager.close();

		return Organization;
	}

	@Override
	public Long insert(Organization organization) throws OpasPersistenceException {

		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Long organizationId = insert(entityManager, organization);

		entityManager.getTransaction().commit();
		entityManager.close();

		return organizationId;
	}

	@Override
	public void update(Organization organization) throws OpasPersistenceException {

		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		update(entityManager, organization);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void delete(Organization organization) throws OpasPersistenceException {
		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		delete(entityManager, organization);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	private Organization select(EntityManager entityManager, Long organizationId) throws OpasPersistenceException {
		Organization organization = entityManager.find(Organization.class, organizationId);
		return organization;
	}

	private Long insert(EntityManager entityManager, Organization organization) throws OpasPersistenceException {
		entityManager.persist(organization);
		return organization.getOrganizationId();
	}

	private void update(EntityManager entityManager, Organization organization) throws OpasPersistenceException {
		entityManager.merge(organization);
	}

	private void delete(EntityManager entityManager, Organization organization) throws OpasPersistenceException {

		organization.setRecordStatus(RecordStatus.INACTIVE);
		entityManager.merge(organization);
	}

}
