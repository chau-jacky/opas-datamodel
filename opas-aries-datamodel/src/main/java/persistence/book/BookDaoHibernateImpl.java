package persistence.book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.RecordStatus;
import model.book.Book;
import persistence.OpasPersistenceConstants;
import persistence.OpasPersistenceException;

public class BookDaoHibernateImpl implements BookDaoIface {

	private EntityManagerFactory sessionFactory;

	BookDaoHibernateImpl() {
		sessionFactory = Persistence.createEntityManagerFactory(OpasPersistenceConstants.PERSISTENCE_UNIT);
	}

	@Override
	public Book select(Long bookId) throws OpasPersistenceException {

		EntityManager entityManager = sessionFactory.createEntityManager();

		Book Book = select(entityManager, bookId);

		entityManager.close();

		return Book;
	}

	@Override
	public Long insert(Book book) throws OpasPersistenceException {

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Long bookId = insert(entityManager, book);

		entityManager.getTransaction().commit();
		entityManager.close();

		return bookId;
	}

	@Override
	public void update(Book book) throws OpasPersistenceException {

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		update(entityManager, book);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void delete(Book book) throws OpasPersistenceException {
	
		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		delete(entityManager, book);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> selectByOrganizationIdAndBookName(Long organizationId, String bookName)
			throws OpasPersistenceException {

		EntityManager entityManager = sessionFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("Book.findByOrganisationIdAndName");
		query.setParameter("organizationId", organizationId);
		query.setParameter("bookName", bookName);
		
		List<Book> tempList = query.getResultList();
		
		entityManager.close();

		return tempList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> selectByOrganizationId(Long organizationId) throws OpasPersistenceException {

		EntityManager entityManager = sessionFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("Book.findByOrganisationId");
		query.setParameter("organizationId", organizationId);
		
		List<Book> tempList = query.getResultList();
		
		entityManager.close();

		return tempList;

	}

	private Book select(EntityManager entityManager, Long bookId) throws OpasPersistenceException {
		Book book = entityManager.find(Book.class, bookId);
		return book;
	}

	private Long insert(EntityManager entityManager, Book book) throws OpasPersistenceException {
		entityManager.persist(book);
		return book.getBookId();
	}

	private void update(EntityManager entityManager, Book book) throws OpasPersistenceException {
		entityManager.merge(book);
	}

	private void delete(EntityManager entityManager, Book book) throws OpasPersistenceException {

		book.setRecordStatus(RecordStatus.INACTIVE);
		entityManager.merge(book);
	}

}
