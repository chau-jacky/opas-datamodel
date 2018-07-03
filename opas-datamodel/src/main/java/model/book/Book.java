package model.book;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import model.OpasOrganizationObject;

@Entity
@SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1, initialValue = 1000)
@NamedQueries({ @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
		@NamedQuery(name = "Book.findByOrganisationIdAndName", query = "SELECT b FROM Book b WHERE b.bookName = :bookName AND b.organization.organizationId = :organizationId"),
		@NamedQuery(name = "Book.findByOrganisationId", query = "SELECT b FROM Book b WHERE b.organization.organizationId = :organizationId") })
@Table(name = "BOOK")
public class Book extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6583292297136670979L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	@Column(name = "BOOK_ID", nullable = false)
	private Long bookId;

	@Column(name = "BOOK_NAME", nullable = false, length = 32)
	private String bookName;

	@Column(name = "BOOK_DESCRIPTION", nullable = false, length = 256)
	private String bookDescription;

	@Enumerated(EnumType.STRING)
	@Column(name = "BOOK_INDICATOR", nullable = false)
	private BookIndicator bookIndicator;

	private boolean offshoreRmbIndicator;
	
	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public BookIndicator getBookIndicator() {
		return bookIndicator;
	}

	public void setBookIndicator(BookIndicator bookIndicator) {
		this.bookIndicator = bookIndicator;
	}

	public boolean isOffshoreRmbIndicator() {
		return offshoreRmbIndicator;
	}

	public void setOffshoreRmbIndicator(boolean offshoreRmbIndicator) {
		this.offshoreRmbIndicator = offshoreRmbIndicator;
	}

}
