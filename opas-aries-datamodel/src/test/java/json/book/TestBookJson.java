package json.book;

import java.sql.Timestamp;

import org.junit.Test;

import com.google.gson.Gson;

import model.RecordStatus;
import model.book.Book;
import model.book.BookIndicator;
import model.organization.Organization;

public class TestBookJson {

	@Test
	public void test() {

		Book book1 = new Book();
		book1.setBookDescription("Sample Book Description");
		book1.setBookIndicator(BookIndicator.TRADING);
		book1.setBookName("Sample Book Name");
		// book1.setOrganizationId(new Long(1001));
		Organization organization = new Organization();
		organization.setOrganizationId(new Long(1001));
		book1.setOrganization(organization);
		
		book1.setLastUpdatedUserId(new Long(1001));
		book1.setLastUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
		book1.setRecordStatus(RecordStatus.ACTIVE);

		Gson gson = new Gson();
		String json = gson.toJson(book1);
		System.out.println(json);

		Book book2 = gson.fromJson(json, Book.class);

		System.out.println(book2.getBookDescription());
		System.out.println(book2.getBookName());
		System.out.println(book2.getBookId());
		System.out.println(book2.getLastUpdatedUserId());
		System.out.println(book2.getOrganization().getOrganizationId());
		System.out.println(book2.getVersion());
		System.out.println(book2.getBookIndicator());
		System.out.println(book2.getLastUpdatedDatetime());
		System.out.println(book2.getRecordStatus());

	}

}
