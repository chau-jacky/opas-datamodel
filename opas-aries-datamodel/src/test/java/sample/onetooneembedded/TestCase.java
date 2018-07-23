package sample.onetooneembedded;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import sample.onetooneembedded.Phone;
import sample.onetooneembedded.PhoneDetails;

public class TestCase {

	@Test
	public void test() {

		Phone phone = new Phone();
		PhoneDetails phoneDetails = new PhoneDetails();
		phone.setNumber("12345678");
		phone.setDetails(phoneDetails);

		phoneDetails.setProvider("CSL");
		phoneDetails.setTechnology("4G");

		EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("opas-aries-datamodel");

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(phone);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
