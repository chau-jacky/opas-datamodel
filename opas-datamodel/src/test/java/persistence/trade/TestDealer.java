package persistence.trade;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import model.RecordStatus;
import model.organization.Organization;
import model.trade.Dealer;

public class TestDealer {

	@Test
	public void test() {

		EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("opas-aries-datamodel");

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Timestamp lastUpdatedDatetime = new Timestamp(System.currentTimeMillis());

		Dealer dealer = new Dealer();

		dealer.setDealerId(new Long(1001));
		dealer.setDealerName("Dealer Name");
		dealer.setLastUpdatedDatetime(lastUpdatedDatetime);
		dealer.setLastUpdatedUserId(new Long(1001));
		// dealer.setOrganizationId(new Long(1001));
		Organization organization = new Organization();
		organization.setOrganizationId(new Long(1001));
		dealer.setOrganization(organization);
		dealer.setVersion(new Long(0));
		dealer.setRecordStatus(RecordStatus.ACTIVE);

		entityManager.persist(dealer);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
