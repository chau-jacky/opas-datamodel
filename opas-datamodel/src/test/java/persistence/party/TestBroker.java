package persistence.party;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import model.RecordStatus;
import model.party.Broker;
import persistence.OpasPersistenceConstants;

public class TestBroker {

	@Test
	public void test() {

		EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Broker broker = new Broker();
		broker.setAccronym("ABCDHKH");
		broker.setName("ABCDHKH Hello");
		broker.setLastUpdatedUserId(new Long(1001));
		broker.setLastUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
		broker.setRecordStatus(RecordStatus.ACTIVE);
		broker.setBicCode("ABCDHKHHH");
		broker.setVersion(new Long(0));

		entityManager.persist(broker);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
