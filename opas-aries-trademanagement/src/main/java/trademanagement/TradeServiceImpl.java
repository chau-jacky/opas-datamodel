package trademanagement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import model.event.EventConfig;
import model.event.EventInstance;
import model.trade.Trade;
import persistence.event.EventConfigDaoHibernateImpl;
import persistence.event.EventConfigDaoIface;

public class TradeServiceImpl implements TradeServiceIface {

	private EventConfigDaoIface eventConfigDao;

	public TradeServiceImpl() {
		eventConfigDao = new EventConfigDaoHibernateImpl();
	}

	public Long save(Trade trade) throws ServiceException {

		EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("opas-aries-datamodel");

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		try {

			Timestamp lastUpdatedDatetime = new Timestamp(System.currentTimeMillis());

			trade.setLastUpdatedDatetime(lastUpdatedDatetime);
			trade.setVersion(new Long(0));

			entityManager.persist(trade);

			List<EventInstance> eventInstances = build(entityManager, trade);
			for (Iterator iterator = eventInstances.iterator(); iterator.hasNext();) {
				EventInstance eventInstance = (EventInstance) iterator.next();
				eventInstance.setLastUpdatedDatetime(lastUpdatedDatetime);
				entityManager.persist(eventInstance);
			}

			for (Iterator iterator = eventInstances.iterator(); iterator.hasNext();) {
				EventInstance eventInstance = (EventInstance) iterator.next();
				publish(eventInstances);
			}

			entityManager.getTransaction().commit();

		} catch (PersistenceException pe) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}

		return trade.getTradeId();

	}

	private List<EventInstance> build(EntityManager entityManager, Trade trade) {

		List<EventInstance> eventInstances = new ArrayList<EventInstance>();

		List<EventConfig> eventConfigs = eventConfigDao.selectByEventClass(entityManager,
				trade.getOrganization().getOrganizationId(), trade.getClass().getName());

		for (Iterator iterator = eventConfigs.iterator(); iterator.hasNext();) {
			EventConfig eventConfig = (EventConfig) iterator.next();

			EventInstance eventInstance = new EventInstance();
			eventInstance.setEventClassId(trade.getTradeId());
			eventInstance.setEventClassName(trade.getClass().getName());
			eventInstance.setEventType(eventConfig.getEventType());
			eventInstance.setOrganization(trade.getOrganization());
			eventInstance.setEventQueueName(eventConfig.getEventQueueName());
			eventInstance.setLastUpdatedUserId(new Long(1001));
			eventInstance.setVersion(new Long(0));

			eventInstances.add(eventInstance);

		}

		return eventInstances;
	}

	private void publish(List<EventInstance> eventInstances) {

		for (Iterator iterator = eventInstances.iterator(); iterator.hasNext();) {
			EventInstance eventInstance = (EventInstance) iterator.next();

			System.out.println("Publish to event to queue" + eventInstance);

		}
	}
}
