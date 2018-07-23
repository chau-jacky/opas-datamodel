package sample.onetomany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import sample.onetomany.Person;
import sample.onetomany.Phone;

public class TestCase {

	@Test
	public void test() {

		Person person = new Person();

		Phone phone1 = new Phone();
		phone1.setNumber("12345678");

		Phone phone2 = new Phone();
		phone2.setNumber("23456789");

		person.getPhones().add(phone1);
		person.getPhones().add(phone2);

		EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("opas-aries-datamodel");

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(person);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
