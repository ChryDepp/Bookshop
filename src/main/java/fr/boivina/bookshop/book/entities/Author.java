package fr.boivina.bookshop.book.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Person infoirmation business object.
 * @author boivina
 *
 */
@Entity
public class Author {

	@Id
	@GeneratedValue
	private long id;

	private String firstName;
	
	private String lastName;
	
	@ManyToMany
	private List<Book> books;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
}
