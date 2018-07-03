package service;

import java.util.List;

import model.book.Book;

public interface BookDaoIface {

	public void save(Book book);

	public List<Book> list();

}
