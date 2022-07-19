package org.crud.repository;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.ObjectId;
import org.crud.entity.Book;
import org.crud.entity.Status;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class BookRepository implements ReactivePanacheMongoRepository<Book> {
	
	public Uni<Book> findByAuthor(String author) {
		return find("author", author).firstResult();
	}
	
	public Multi<Book> findBooksPublishing() {
		return stream("status", "Publicando");
	}
	
	public static Uni<Book> updateBook(String id, Book book) {
		Uni<Book> uniBook = book.findById(new ObjectId(id));
		return uniBook.onItem()
				.transform(libro -> {
					book.setName(book.getName());
					return book;
				}).call(libro -> book.persistOrUpdate());
	}
	
	public Multi<Book> streamAllBook() {
		return streamAll();
	}
}