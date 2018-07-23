package persistence.party;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import model.RecordStatus;
import model.party.Counterparty;
import persistence.OpasPersistenceConstants;

public class TestCounterparty {

	@Test
	public void test() {

		EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Counterparty counterparty = new Counterparty();
		counterparty.setAcronym("ABCDHKH");
		counterparty.setName("ABCDHKH Hello");
		counterparty.setLastUpdatedUserId(new Long(1001));
		counterparty.setLastUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
		counterparty.setRecordStatus(RecordStatus.ACTIVE);
		counterparty.setBicCode("ABCDHKHHH");
		counterparty.setVersion(new Long(0));

		entityManager.persist(counterparty);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
