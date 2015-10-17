package fr.boivina.bookshop.book.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.boivina.bookshop.book.entities.Author;
import fr.boivina.bookshop.book.entities.Book;
import fr.boivina.bookshop.book.service.BookService;

@Named
@ViewScoped
public class BookController {
	
	@Inject
	private BookService bookService;
	
	private String bookNumber = "10";
	
	private List<Book> books;

	@PostConstruct
	public void init(){
		
		Author author = new Author();
		author.setFirstName("Arnaud");
		author.setLastName("Boivin");
		List<Author> authors = new ArrayList<>();
		authors.add(author);
		
		Book book = new Book();
		book.setAuthors(authors);
		book.setIsbn("1-161-1-111");
		book.setSummary("Summary of the book");
		bookService.save(book);
		
		book.
		
		
		books = bookService.findAll();
		books.stream().forEach(bo -> bo.getAuthors().forEach(auth -> auth.getFirstName()));
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}
}