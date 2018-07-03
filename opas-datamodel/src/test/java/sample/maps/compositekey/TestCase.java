package sample.maps.compositekey;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import sample.maps.compositekey.Person;
import sample.maps.compositekey.PersonPk;

public class TestCase {

	@Test
	public void test() {

		Person person = new Person();

		PersonPk personPk = new PersonPk();
		personPk.setId(new Long(System.currentTimeMillis()));

		person.setId(personPk);

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
