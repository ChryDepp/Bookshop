package fr.boivina.bookshop.book.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.boivina.bookshop.book.entities.Book;

@ApplicationScoped
@Service
@Transactional
public class BookService {
	
	public BookService() {
		super();
	}

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Book> findAll() {
		CriteriaQuery<Book> query = entityManager.getCriteriaBuilder().createQuery(Book.class);
		query.select(query.from(Book.class));
		return entityManager.createQuery(query).getResultList();
	}
	
	public void save(Book book) {
		book.getAuthors().stream().forEach(author -> entityManager.persist(author));
		entityManager.persist(book);
	}
}
