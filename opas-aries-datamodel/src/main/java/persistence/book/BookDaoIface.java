package persistence.book;

import java.util.List;

import model.book.Book;
import persistence.OpasPersistenceException;

public interface BookDaoIface {

	public Book select(Long bookId) throws OpasPersistenceException;

	public Long insert(Book book) throws OpasPersistenceException;

	public void update(Book book) throws OpasPersistenceException;

	public void delete(Book book) throws OpasPersistenceException;

	public List<Book> selectByOrganizationIdAndBookName(Long organizationId, String bookName)
			throws OpasPersistenceException;

	public List<Book> selectByOrganizationId(Long organizationId) throws OpasPersistenceException;

}
