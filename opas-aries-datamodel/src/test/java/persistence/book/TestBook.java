package persistence.book;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.RecordStatus;
import model.book.Book;
import model.book.BookIndicator;
import model.organization.Organization;
import persistence.OpasPersistenceException;
import persistence.TestConstants;
import persistence.organization.OrganizationDaoHibernateImpl;
import persistence.organization.OrganizationDaoIface;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBook {

	private BookDaoIface dao;
	private OrganizationDaoIface organizationDao;
	private Long bookId;

	@Before
	public void setUp() throws Exception {
		dao = new BookDaoHibernateImpl();
		organizationDao = new OrganizationDaoHibernateImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws OpasPersistenceException {

		// Create and save a new book
		Book book1 = new Book();
		book1.setLastUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
		book1.setLastUpdatedUserId(TestConstants.USER_ID);
		book1.setRecordStatus(RecordStatus.ACTIVE);
		//Organization organization = new Organization();
		//organization.setOrganizationId(TestConstants.ORGANIZATION_ID);
		Organization organization = organizationDao.select(TestConstants.ORGANIZATION_ID);
		book1.setOrganization(organization);
		// book1.setOrganizationId(TestConstants.ORGANIZATION_ID);

		book1.setBookName("Book1");
		book1.setBookIndicator(BookIndicator.HEDGING);
		book1.setBookDescription("Description of Book 1");

		System.out.println("Before book 1 insert");
		bookId = dao.insert(book1);
		System.out.println("After book 1 insert");

		// Select the book created using the bookId
		Book book2 = dao.select(bookId);
		System.out.println("After book 2 select");

		// Check all attributes
		Assert.assertEquals("LastUpdatedDatetime", book1.getLastUpdatedDatetime(), book2.getLastUpdatedDatetime());
		Assert.assertEquals("LastUpdatedUserId", book1.getLastUpdatedUserId(), book2.getLastUpdatedUserId());
		Assert.assertEquals("Version", book1.getVersion(), book2.getVersion());
		Assert.assertEquals("RecordStatus", book1.getRecordStatus(), book2.getRecordStatus());
		Assert.assertEquals("OrganizationId", book1.getOrganization().getOrganizationId(), book2.getOrganization().getOrganizationId());

		Assert.assertEquals("BookId", book1.getBookId(), book2.getBookId());
		Assert.assertEquals("BookIndicator", book1.getBookIndicator(), book2.getBookIndicator());
		Assert.assertEquals("BookDescription", book1.getBookDescription(), book2.getBookDescription());

		// Update a book
		Book book3 = dao.select(bookId);
		book3.setBookDescription(book3.getBookDescription() + System.currentTimeMillis());
		dao.update(book3);

		Book book4 = dao.select(bookId);

		// Check updated attribute(s)
		Assert.assertEquals("BookDescription", book3.getBookDescription(), book4.getBookDescription());

		Book book5 = dao.select(bookId);
		Book book6 = dao.select(bookId);

		book5.setBookDescription(book5.getBookDescription() + System.currentTimeMillis());
		book6.setBookDescription(book6.getBookDescription() + (System.currentTimeMillis() + 1));

		try {

			dao.update(book5);

			dao.update(book6);
			// fail if update is successful

			Assert.fail();

		} catch (OptimisticLockException ole) {
			// do nothing
		}

		// delete book
		Book book7 = dao.select(bookId);
		dao.delete(book7);

		Book book8 = dao.select(bookId);
		Assert.assertEquals("RecordStatus", book7.getRecordStatus(), book8.getRecordStatus());

		@SuppressWarnings("unused")
		List<Book> book1s = dao.selectByOrganizationIdAndBookName(TestConstants.ORGANIZATION_ID,
				TestConstants.BOOK_NAME);

		@SuppressWarnings("unused")
		List<Book> book2s = dao.selectByOrganizationId(TestConstants.ORGANIZATION_ID);
	}

}
