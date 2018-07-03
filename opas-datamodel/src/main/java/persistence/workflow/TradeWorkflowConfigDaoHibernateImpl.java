package persistence.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.RecordStatus;
import model.workflow.TradeAction;
import model.workflow.TradeStatus;
import model.workflow.TradeWorkflowConfig;
import persistence.OpasPersistenceConstants;
import persistence.OpasPersistenceException;

public class TradeWorkflowConfigDaoHibernateImpl implements TradeWorkflowConfigDaoIface {

	@Override
	public TradeWorkflowConfig select(Long configId) throws OpasPersistenceException {

		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();

		TradeWorkflowConfig tradeWorkflowConfig = select(entityManager, configId);

		entityManager.close();

		return tradeWorkflowConfig;
	}

	@Override
	public Long insert(TradeWorkflowConfig tradeWorkflowConfig) throws OpasPersistenceException {

		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Long configId = insert(entityManager, tradeWorkflowConfig);

		entityManager.getTransaction().commit();
		entityManager.close();

		return configId;
	}

	@Override
	public void update(TradeWorkflowConfig tradeWorkflowConfig) throws OpasPersistenceException {

		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		update(entityManager, tradeWorkflowConfig);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void delete(TradeWorkflowConfig tradeWorkflowConfig) throws OpasPersistenceException {
		EntityManagerFactory sessionFactory = Persistence
				.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		delete(entityManager, tradeWorkflowConfig);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	private TradeWorkflowConfig select(EntityManager entityManager, Long configId) throws OpasPersistenceException {
		TradeWorkflowConfig tradeWorkflowConfig = entityManager.find(TradeWorkflowConfig.class, configId);
		return tradeWorkflowConfig;
	}

	private Long insert(EntityManager entityManager, TradeWorkflowConfig tradeWorkflowConfig)
			throws OpasPersistenceException {
		entityManager.persist(tradeWorkflowConfig);
		return tradeWorkflowConfig.getTradeWorkflowConfigId();
	}

	private void update(EntityManager entityManager, TradeWorkflowConfig tradeWorkflowConfig)
			throws OpasPersistenceException {
		entityManager.merge(tradeWorkflowConfig);
	}

	private void delete(EntityManager entityManager, TradeWorkflowConfig tradeWorkflowConfig)
			throws OpasPersistenceException {

		tradeWorkflowConfig.setRecordStatus(RecordStatus.INACTIVE);
		entityManager.merge(tradeWorkflowConfig);
	}

	@Override
	public List<TradeWorkflowConfig> selectConfigByOrganizationIdAndTradeClassNameAndFromStatusAndAction(
			Long organizationId, String tradeClassName, TradeStatus fromStatus, TradeAction tradeAction)
			throws OpasPersistenceException {
		List<TradeWorkflowConfig> results = new ArrayList<TradeWorkflowConfig>();

		return results;
	}

	@Override
	public List<TradeWorkflowConfig> selectStpConfigByOrganizationIdAndTradeClassNameAndFromStatusAndAction(
			Long organizationId, String tradeClassName, TradeStatus fromStatus, TradeAction fromAction)
			throws OpasPersistenceException {
		List<TradeWorkflowConfig> results = new ArrayList<TradeWorkflowConfig>();

		return results;
	}

}
