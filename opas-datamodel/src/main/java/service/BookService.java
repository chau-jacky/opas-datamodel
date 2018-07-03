package service;

import java.util.List;

import model.book.Book;

public interface BookService {

	public void save(Book book);

	public List<Book> list();

}
