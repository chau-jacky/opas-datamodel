package sample.maps;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import sample.maps.Person;

public class TestCase {

	@Test
	public void test() {

		Person person = new Person();

		person.getAttributes().put("Key01", "Value01");
		person.getAttributes().put("Key02", "Value02");
		person.getAttributes().put("Key03", "Value03");

		EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("opas-aries-datamodel");

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(person);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
