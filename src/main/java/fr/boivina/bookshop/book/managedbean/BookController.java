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
	
	private Book book = new Book();
	
	private List<Book> books;

	@PostConstruct
	public void init(){
		
		Author author = new Author();
		author.setFirstName("Arnaud");
		author.setLastName("Boivin");
		author.setBooks(new ArrayList<>());
		List<Author> authors = new ArrayList<>();
		authors.add(author);
		
		Book book = new Book();
		book.setAuthors(authors);
		book.setIsbn("1-161-1-111");
		book.setSummary("Summary of the book");
		
		authors.forEach(auth -> author.getBooks().add(book));
		
		bookService.save(book);
		
		refreshBooks();
	}

	private void refreshBooks() {
		books = bookService.findAll();
	}
	
	public void saveBook(){
		bookService.save(book);
		refreshBooks();
		
		
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}